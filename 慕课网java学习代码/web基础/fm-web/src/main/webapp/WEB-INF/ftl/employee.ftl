<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>员工列表</title>
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet"></link>
    
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>

    <style type="text/css">
        .pagination {
            margin: 0px
        }

        .pagination > li > a, .pagination > li > span {
            margin: 0 5px;
            border: 1px solid #dddddd;
        }

        .glyphicon {
            margin-right: 3px;
        }

        .form-control[readonly] {
            cursor: pointer;
            background-color: white;
        }
        #dlgPhoto .modal-body{
            text-align: center;
        }
        .preview{

            max-width: 500px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <h1 style="text-align: center">IMOOC员工信息表</h1>
        <div class="panel panel-default">
            <div class="clearfix panel-heading ">
                <div class="input-group" style="width: 500px;">
                    
                </div>
            </div>

            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>员工编号</th>
                    <th>姓名</th>
                    <th>部门</th>
                    <th>职务</th>
                    <th>工资</th>
                    <th>&nbsp;</th>
                </tr>
                </thead>
                <tbody>
                <#list list as e>
                <tr>
                    <td>${e_index + 1}</td>
                    <td>${e.empno?string("0")}</td>
                    <td>${e.ename}</td>
                    <td>${e.edepartment}</td>
                    <td>${e.job}</td>
                    <td style="color: red;font-weight: bold">￥${e.salary?string("0.00")}</td>
                </tr>
                </#list>
                
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>