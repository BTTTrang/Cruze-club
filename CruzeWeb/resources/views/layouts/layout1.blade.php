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

<style>
    
    @import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap');
    body {
        font-family: 'IBM Plex Sans', sans-serif;
    }
    .cz-sidebar {
        width: 280px; height: 100%; position: fixed;
    }

    main {
        padding: 40px;
        margin-left: 280px;
    }

    .modal-body input {
        margin-bottom: 10px;
    }
</style>

@yield('scripts')



<body>
    @include('layouts.partials.sidebar')


    <main>
        
        @include('layouts.partials.breadcrumb' , ['breadcrumb' => $breadcrumb])
        @yield('content')

    </main>
</body>

</html>