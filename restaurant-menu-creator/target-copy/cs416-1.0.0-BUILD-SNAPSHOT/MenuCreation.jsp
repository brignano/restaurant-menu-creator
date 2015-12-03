<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <title>Menu Creation Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type = "text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <style type="text/css">
            .jumbotron{
                color:black;
            }
        </style>

        <script type="text/javascript">
            var $menuSection = 1;
            function buildItemSection()
            {
                var $container = this.parentNode,
                        $item, $field, $br = document.createElement('br');

                $item = document.createElement('div');
                $item.style.margin = '3px';

                $field = document.createElement('span');
                $field.innerHTML = 'Menu Item Name:';
                $field.style.margin = '0px 10px';
                $item.appendChild($field);

                $field = document.createElement('input');
                $field.id = 'name' + this.parentNode.id;
                $field.name = 'name' + this.parentNode.id;
                $field.className = 'form-control';
                $field.type = 'text';
                $field.size = '20';
                $field.required = 'true';
                $item.appendChild($field);

                $field = document.createElement('span');
                $field.innerHTML = 'Price:';
                $field.style.margin = '0px 10px';
                $item.appendChild($field);

                $field = document.createElement('input');
                $field.id = 'price' + this.parentNode.id;
                $field.name = 'price' + this.parentNode.id;
                $field.className = 'form-control';
                $field.type = 'text';
                $field.size = '10';
                $field.required = 'true';
                $item.appendChild($field);

                $item.appendChild($br);
                $item.appendChild($br);

                $field = document.createElement('span');
                $field.innerHTML = 'Description:';
                $field.style.margin = '0px 10px';
                $item.appendChild($field);

                $field = document.createElement('textarea');
                $field.id = 'description' + this.parentNode.id;
                $field.name = 'description' + this.parentNode.id;
                $field.className = 'form-control';
                $field.type = 'text';
                $field.cols = '50';
                $field.rows = '3';
                $field.required = 'true';
                $item.appendChild($field);

                $item.appendChild($br);

                $container.appendChild($item);

            }

            function buildMenuSection()
            {

                var $container = document.getElementById('MenuFields'),
                        $item, $field, $label, $br = document.createElement('br');


                $field = document.createElement('div');
                $field.id = $menuSection;
                $item = $field;
                $item.style.margin = '3px';

                $field = document.createElement('h3');
                $field.innerHTML = 'Menu Section ' + $menuSection + ' Description:';
                $field.style.margin = '0px 10px';
                $item.appendChild($field);

                $field = document.createElement('input');
                $field.id = 'section' + $menuSection;
                $field.name = 'section' + $menuSection;
                $field.className = 'form-control';
                $field.type = 'text';
                $field.size = '50';
                $field.required = 'true';
                $item.appendChild($field);

                $item.appendChild($br);

                $field = document.createElement('button');
                $field.type = 'button';
                $field.id = 'but' + $menuSection;
                $field.className = "btn btn-info";
                $label = document.createTextNode('New Item');
                $field.appendChild($label);
                $field.onclick = buildItemSection;
                $item.appendChild($field);
                $container.appendChild($item);

                $field = document.createElement('button');
                $field.type = 'button';
                $field.id = 'remove' + $menuSection;
                $field.className = "btn btn-danger";
                $label = document.createTextNode('Remove Last Item');
                $field.appendChild($label);
                $field.onclick = removeItem;
                $item.appendChild($field);
                $container.appendChild($item);

                $menuSection++;
                document.getElementById("sections").value = $menuSection;

            }
            function removeItem() {

                if (this.parentNode.lastChild !== this) {
                    this.parentNode.lastChild.remove();
                }
            }
            function removeMenu() {
                var $field = document.getElementById('MenuFields');

                $field.lastChild.remove();
                $menuSection--;
                document.getElementById("sections").value = $menuSection;

            }

        </script>

    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <form method="post" action="menusave">
                    <h3>Restaurant Name</h3>
                    <input type = "text" id = "restName" name="restName" size = "50" class = 'form-control' placeholder = "Restaurant Name" required>
                    <h3>Restaurant Phone Number</h3>
                    <input type = "text" id = "restPhone" size = "50" class = 'form-control' placeholder = "Restaurant Phone Number" required>
                    <h3>Street Address</h3>
                    <input type = "text" id = "restStreet" size = "50" class = 'form-control' placeholder = "Restaurant Street Address" required>
                    <h3>City</h3>
                    <input type = "text" id = "restCity" size = "50" class = 'form-control' placeholder = "Restaurant City" required>
                    <h3>State</h3>
                    <input type = "text" id = "restState" size = "50" class = 'form-control' placeholder = "Restaurant State" required>
                    <h3>Zip Code</h3>
                    <input type = "text" id = "restZip" size = "50" class = 'form-control' placeholder = "Restaurant Zip Code" required>

                    </br>
                    <button type ="button" class = "btn btn-primary" onclick="buildMenuSection();">New Menu Section</button>
                    <button type ="button" class = "btn btn-danger" onclick ="removeMenu();">Remove Menu Section</button>
                    <div id="MenuFields" style="margin: 20px 0px;"></div>
                    <input type="submit" class ="btn btn-success" >
                </form>
                <form>
                    <input type="hidden" id="sections" name="sections"/>
                </form>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>