#!/usr/bin/env perl

use strict;
use warnings;
use 5.022_000;

package Template::Menu::Maker;

use File::Spec::Functions qw(catfile catdir);
use FindBin;
use Getopt::Long;
use Mojo::DOM;
use PDF::WebKit;

## ======
## Script Arguments
## ======
my $use_message = 'usage: TemplateMaker.pl --template template/folder/path' . "\n"
	. '--file path/to/file.pdf --menu_title name --image path/to/image[.jpg/.png]' . "\n"
	. '--content *"SUBMENU-HEADER" ^"SUBMENU-ITEM" [!"SUBMENU-ITEM-DESC" $"SUBMENU-ITEM-PRICE"]';

my (
	$template, $pdf_name, $menu_title, $image
) = (0) x 4;

my @content;

GetOptions(
	"template=s" => \$template,
	"file=s"     => \$pdf_name,

	"menu_title=s" => \$menu_title,
	"image=s"      => \$image,
	"content=s{,}" => \@content
) or die($use_message);

unless ($template && $pdf_name && $menu_title) {
	die($use_message);
}

exit make_pdf();


## ======
## Uses Mojo::DOM to edit the template HTML file with the data supplied
## through the command line arguments.
##
## Creates the PDF file using wkhtmltopdf and the customized HTML file.
## ======
sub make_pdf {
	my $html_path = catfile(
		$FindBin::Bin,
		$template,
		"$template.html"
	);

	my $css_path = catfile(
		$FindBin::Bin,
		$template,
		"$template.css"
	);

	my $pdf_output = $pdf_name;

	# Slurp the contents of the supplied HTML template
	open my $html_fh, '<', $html_path or die('Couldn\'t open file');
	my $html_content;
	{ local $/; $html_content = <$html_fh> };
	close($html_fh) or die('Couldn\'t close file');

	# Load the HTML template into the DOM parser for editing
	my $dom = Mojo::DOM->new($html_content);

	# Add the menu name
	$dom->at('h1#menu-title')
		->content("$menu_title")
		->root;

	if ($image) {
		my $image_html = "<img src=\"$image\"></img>";
		$dom->at('div#menu-name')
			->append($image_html)
			->root;
	}

	my $extract       = extract_content();
	my $submenu_index = 0;
	my $mimic         = $dom->at('div.menu-section');

	$dom->at('div.menu-section')
		->remove
		->root;

	# Generate the submenu HTML from the supplied content arguments
	for my $submenu (@$extract) {
		my $inner_dom   = Mojo::DOM->new($mimic);
		my $elem_number = 1;
		my $subsection  = $submenu_index + 1;

		$inner_dom->at('div.menu-section')
			->attr(id => "menu-section-$subsection");
		$inner_dom->at('h2.submenu-title')
			->attr(id => "section-$subsection-header");
		$inner_dom->at("h2#section-$subsection-header")
			->content($submenu->{'head'})
			->root;

		for my $elem (keys %{$submenu->{'sections'}}) {
			my $elem_info = $submenu->{'sections'}->{$elem};
			# Price should always be passed after the description
			my $elem_desc = $elem_info->[0];
			my $elem_price = $elem_info->[1];

			unless (defined $elem_desc) {
				$elem_desc = '';
			}

			unless (defined $elem_price) {
				$elem_price = '';
			}

			my $element_html = qq {
				<li class="section-$subsection-element" id="section-$subsection-e$elem_number">
					<ul class="section-sublist">
						<li class="section-title">$elem</li>
						<li class="section-desc">$elem_desc</li>
					</ul>
				</li>
			};

			$inner_dom->at('ul#element-list')
				->append_content($element_html)
				->root;
			$elem_number++;
		}

		$submenu_index++;
		$dom->at('body')
			->append_content($inner_dom)
			->root;
	}

	# Use the wkhtmltopdf wrapper to create the PDF menu
	PDF::WebKit->configure(sub {
		$_->wkhtmltopdf('/usr/local/bin/wkhtmltopdf');
	});

	### TODO: Customize the desired PDF settings, these are just for testing.
	my $webkit = PDF::WebKit->new(
		\$dom->to_string,
		page_size    => 'Letter',
		footer_right => '[date]'
	);
	push @{$webkit->stylesheets}, $css_path;

	$webkit->to_file($pdf_output);
	return 1;
}

## ======
## Creates an array which contains hashref elements.
##
## The hashref uses the element title as the key and has
## an arrayref value containing the description and price
## of the element.
##
## Returns an arrayref.
## ======
sub extract_content {
	my @extracted_content;

	my $header_token = '*';
	my $item_token   = '^';
	my $desc_token   = '!';
	my $price_token  = '$';
	my $index = 0;
	my ($c_header_index, $c_item_key);

	while (scalar @content > 0) {
		my $line  = shift @content;
		my $input = substr $line, 1;
		my $token = substr $line, 0, 1;

		if ($token eq $header_token) {
			push @extracted_content, {
				head     => $input,
				sections => {}
			};
			$c_header_index = $index;
			$index++;
		}

		elsif ($token eq $item_token) {
			my %c_hash = %{$extracted_content[$c_header_index]};
			$c_hash{'sections'}->{$input} = [];
			$extracted_content[$c_header_index] = \%c_hash;
			$c_item_key = $input;
		}

		elsif ($token eq $desc_token) {
			push @{$extracted_content[$c_header_index]{'sections'}->{$c_item_key}}, $input;
		}

		elsif ($token eq $price_token) {
			say "PRICE $input";
		}
	}

	return \@extracted_content;
}