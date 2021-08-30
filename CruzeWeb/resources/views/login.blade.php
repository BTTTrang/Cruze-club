<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron text-center">
  <h1>Cruze Club Admin System</h1>
  <p>Vui lòng đăng nhập để truy cập vào hệ thống</p> 
</div>
  
<div class="container" style="margin: 0 auto; width: 400px">
    <form method="POST" action="{{ route('postLogin') }}">
        @csrf 
        <div class="form-group">
          <label>Tên đăng nhập</label>
          <input class="form-control" placeholder="Tên đăng nhập" name="username">
        </div>
        <div class="form-group">
          <label>Mật khẩu</label>
          <input type="password" class="form-control" placeholder="Mật khẩu" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>
