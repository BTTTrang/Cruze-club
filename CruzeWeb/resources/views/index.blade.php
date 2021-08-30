@extends('layouts.layout1' , ['breadcrumb' => "Duyệt đăng ký"])

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
          <a href="" id="plateacceptmodal"><button type="button" class="btn btn-success">Duyệt</button></a>
        </div>
      </div>
    </div>
  </div>




    <!-- Add New Ticket Modal -->
    <div class="modal fade" id="addNewTicketModal" tabindex="-1" role="dialog" aria-labelledby="addNewTicketModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form method="POST" action="{{ route('createTicket') }}" enctype="multipart/form-data">
                @csrf
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addNewTicketModalLabel">Tạo mới</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input class="form-control" placeholder="Tên đầy đủ" name="fullname" required>
                        <input class="form-control" placeholder="Tên fb" name="fb_name" required>
                        <input class="form-control" placeholder="Địa chỉ" name="address" required>
                        <input class="form-control" placeholder="Tem số (Không bắt buộc)" name="stamp">
                        <input class="form-control" placeholder="Số điện thoại" name="phone" required>
                        <input class="form-control" placeholder="Giới tính" name="gender" required>
                        <input class="form-control" placeholder="Biển số xe" name="plate" required>
                        <label>Hình ảnh biển số xe: </label>
                        <input class="form-control" type="file" name="plateimage" required>
                  
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Tạo mới</button>
                    </div>
                </div>
            </form>

        </div>
    </div>




<button class="btn btn-primary"  data-toggle="modal" data-target="#addNewTicketModal">Tạo mới</button>
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
        @isset($pending_verification)
        @foreach ($pending_verification as $ticket_id => $item)
        <tr>
            <th scope="row">{{ $loop->index + 1}}</th>
            <td>{{$item["fullname"]}}</td>
            <td>{{$item["phone"]}}</td>
            <td>{{$item["gender"]}}</td>
            <td style="background: lightslategray; color: white;">{{$item["plate"]}}<button class="btn btn-light" style="float: right" onclick='showPlateImage("/image/{{$item["fileName"]}}" , "{{$item["plate"]}}" , "{{$ticket_id}}")' data-toggle="modal" data-target="#viewImageModal">Xem ảnh</button></td>
            <td>
                <button class="btn btn-info" onclick="showDetail({{$loop->index}} , this)" style="width: 150px;">Xem chi tiết</button>
                {{-- <a href="{{ route('acceptTicket', ['ticket'=>$ticket_id]) }}"><button class="btn btn-success">V</button></a> --}}
                <a href="{{ route('denyTicket', ['ticket'=>$ticket_id]) }}"><button class="btn btn-danger">X</button></a>
            </td>
        </tr>
        <tr >
            <td colspan="6" style="padding: 0%;">
                <div id="detail{{$loop->index}}" style="background: turquoise; color: white; display: none; padding: 10px">
                    <strong>Tên facebook:</strong> {{$item["fb_name"]}}
                    <br>
                    <strong>Địa chỉ:</strong> {{$item["address"]}}
                    <br>
                    <strong>Tem số:</strong> {{$item["stamp"] ?? null}}
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
        $("#plateacceptmodal").attr("href","accept_ticket/" + ticket);
    }
</script>

@endsection