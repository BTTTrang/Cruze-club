@extends('layouts.layout1' , ['breadcrumb' => "Quản lý code"])

@section('content')

    <!-- Add Code Modal -->
    <div class="modal fade" id="addCodeModal" tabindex="-1" role="dialog" aria-labelledby="addCodeModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form method="POST" action="{{ route('createCarCode') }}">
                @csrf
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCodeModalLabel">Thêm code</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input class="form-control" placeholder="Tên code" name="codename" required>
                        <textarea class="form-control" placeholder="Mô tả code" rows="4" style="margin-top: 20px;" name="codedes" required></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Thêm code</button>
                    </div>
                </div>
            </form>

        </div>
    </div>


<button class="btn btn-primary" data-toggle="modal" data-target="#addCodeModal">Thêm code</button>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">Tên code</th>
            <th scope="col">Mô tả code</th>
            <th scope="col">Hành động</th>
        </tr>
    </thead>
    <tbody>
        @isset($carCodes) 
            @foreach ($carCodes as $code_id => $item)
            <tr>
                <td>{{$item["codename"]}}</td>
                <td>{{$item["codedes"]}}</td>
                <td>
                    <a href="{{ route('deleteCarCode', ['car_code'=>$code_id]) }}"><button class="btn btn-danger">Xóa</button></a>
                </td>
            </tr>
            @endforeach
        @endisset



    </tbody>
</table>
@endsection

@section('scripts')

@endsection