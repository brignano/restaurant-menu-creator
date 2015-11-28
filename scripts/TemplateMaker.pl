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
my $use_message = 'usage: TemplateMaker.pl --template path/to/file.html '
	. '--file pdf_name --menu_title name';

my (
	$template, $pdf_name, $menu_title
) = (0) x 3;

my @content;

GetOptions(
	"template=s" => \$template,
	"file=s"     => \$pdf_name,

	"menu_title=s" => \$menu_title,
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

	my $pdf_output = catfile(
		$FindBin::Bin,
		"$pdf_name.pdf"
	);

	# Slurp the contents of the supplied HTML template
	open my $html_fh, '<', $html_path or die('Couldn\'t open file');
	my $html_content;
	{ local $/; $html_content = <$html_fh> };
	close($html_fh) or die('Couldn\'t close file');

	# Use Mojo::DOM to edit the HTML template
	my $dom = Mojo::DOM->new($html_content);

	# Add the menu name
	$dom->at('h1#menu-title')
		->content("$menu_title")
		->root;

	### TODO: Add logo if one was given

	# Copy and modify the template for a subsection
	my $subsection = '2';
	my $mimic      = $dom->at('div#menu-section-1');
	my $inner_dom  = Mojo::DOM->new($mimic);

	$inner_dom->at('div#menu-section-1')
		->attr(id => "menu-section-2");

	$inner_dom->at('ul#element-list');
	for my $z (1 .. 4) {
		my $element_html = qq {
			<li class="section-$subsection-element" id="section-$subsection-e$z">
				<ul class="section-sublist">
					<li class="section-title"></li>
					<li class="section-desc"></li>
				</ul>
			</li>
		};

		$inner_dom->append_content($element_html);
	}

	say $inner_dom;

	### DEBUG: Don't create a pdf file
	return 1;

	my %extracted_content;

	my $header_token = '*';
	my $item_token   = '^';
	my $desc_token   = '!';

	my $c_header_key;
	my $c_item_key;

	while (scalar @content > 0) {
		my $line  = shift @content;
		my $input = substr $line, 1;
		my $token = substr $line, 0, 1;

		if ($token eq $header_token) {
			$extracted_content{$input} = {};
			$c_header_key = $input;
		}

		elsif ($token eq $item_token) {
			$extracted_content{$c_header_key}->{$input} = 0;
			$c_item_key = $input;
		}

		elsif ($token eq $desc_token) {
			$extracted_content{$c_header_key}->{$c_item_key} = $input;
		}
	}

	# Generate the PDF from the customized template
	PDF::WebKit->configure(sub {
		$_->wkhtmltopdf('C:/wkhtmltopdf/bin/wkhtmltopdf.exe');
	});

	my $webkit = PDF::WebKit->new(
		\$dom->to_string,
		page_size    => 'Letter',
		footer_right => '[date]'
	);

	$webkit->to_file($pdf_output);
	return 1;
}