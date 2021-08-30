@extends('layouts.layout1', ['breadcrumb' => "Xem danh sách đăng ký"])

@section('content')
  <!-- Modal -->
  <div class="modal fade bd-example-modal-lg" id="viewImageModal" tabindex="-1" role="dialog" aria-labelledby="viewImageModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="viewImageModalLabel">Biển số xe: </h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <img id="plateimageholder" src="" style="width: 100%">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        
        </div>
      </div>
    </div>
  </div>

<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Họ tên</th>
            <th scope="col">Số điện thoại</th>
            <th scope="col">Giới tính</th>
            <th scope="col">Biển số xe</th>
            <th scope="col">Hành động</th>
        </tr>
    </thead>
    <tbody>
        @isset($registered)
        @foreach ($registered as $ticket_id => $item)
        <tr>
            <th scope="row">{{$loop->index + 1}}</th>
            <td>{{$item["fullname"]}}</td>
            <td>{{$item["phone"]}}</td>
            <td>{{$item["gender"]}}</td>
            <td style="background: lightslategray; color: white;">{{$item["plate"]}}<button class="btn btn-light" style="float: right" onclick='showPlateImage("/image/{{$item["fileName"]}}" , "{{$item["plate"]}}" , "{{$ticket_id}}")' data-toggle="modal" data-target="#viewImageModal">Xem ảnh</button></td>
            <td>
                <button class="btn btn-info" onclick="showDetail({{$loop->index}} , this)" style="width: 150px;">Xem chi tiết</button>
                <a href="{{ route('deleteVerifiedTicket', ['ticket'=>$ticket_id]) }}"><button class="btn btn-danger">X</button></a>
            </td>
        </tr>
        <tr >
            <td colspan="6" style="padding: 0%;">
                <div id="detail{{$loop->index}}" style="background: turquoise; color: white; display: none; padding: 10px">
                    <strong>Tên facebook:</strong> {{$item["fb_name"]}}
                    <br>
                    <strong>Địa chỉ:</strong> {{$item["address"]}}
                    <br>
                    <strong>Tem số:</strong> {{$item["stamp"]}}
                </div>
    
            </td>
        </tr> 
        @endforeach
        @endisset
       
    </tbody>
</table>
@endsection

@section('scripts')
<script>
    function showDetail(index , evt){
        $("#detail"+index).slideToggle();
        $current = $(evt).html();
        if ($current == "Xem chi tiết") {
            $(evt).html("Ẩn chi tiết")
        }
        else {
            $(evt).html("Xem chi tiết")
        }
    }
    function showPlateImage(imgsrc , plate , ticket){
        $("#plateimageholder").attr("src",imgsrc);
        $("#viewImageModalLabel").html("Biển số xe: " + plate);

    }
</script>

@endsection