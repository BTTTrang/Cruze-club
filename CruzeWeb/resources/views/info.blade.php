@extends('layouts.layout1' , ['breadcrumb' => "Quản lý giới thiệu"])

@section('content')
    <h2>Giới thiệu</h2>
    <form method="POST" action="{{ route('postInfo') }}">
        @csrf
        <textarea class="form-control" rows="7" name="info" required>{{$info ?? null}}</textarea>
        <button class="btn btn-primary" type="submit">Cập nhật</button>
    </form>

@endsection