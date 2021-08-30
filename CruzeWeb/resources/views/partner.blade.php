@extends('layouts.layout1' , ['breadcrumb' => "Quản lý bạn đồng hành"])

@section('content')

<div class="modal fade" id="addAgencyModal" tabindex="-1" role="dialog" aria-labelledby="addAgencyModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addAgencyModalLabel">Thêm đại lý</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <form method="POST" action="{{ route('postPartner') }}">
            @csrf
            <div class="modal-body">
                <input id="customMien" name="mien" hidden>
                <input class="form-control" placeholder="Tên đại lý" name="agencyname" required>
                <input class="form-control" placeholder="Địa chỉ" name="agencyaddress" required>
                <input class="form-control" type="number" placeholder="Hotline" name="hotline" required>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="submit" class="btn btn-primary">Thêm</button>
              </div>
        </form>

      </div>
    </div>
</div>


<div class="row">
    <div class="col-6">
        <h3 style="float: left;">Chăm sóc - Độ Xe</h3>
        <button class="btn btn-info" style="float: right;" data-toggle="modal" data-target="#addAgencyModal" onclick="chuyenMien('doxe')">Thêm</button>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Tên gara</th>
                    <th scope="col">Hành động</th>
                </tr>
            </thead>
            <tbody>
                @isset($doXe)
                @foreach ($doXe as $agency_id => $item)
                <tr>
                    <td>{{$loop->index + 1}}</td>
                    <td>{{$item["agencyname"]}}</td>
                    <td>
                        <button class="btn btn-info" onclick="showAgencyDetail('doxe' , {{$loop->index}} , this)" style="width: 58px">Xem</button>
                        <a href="{{ route('deletePartner', ['mien' => 'doxe' , 'agency_id'=>$agency_id]) }}"><button class="btn btn-danger">Xóa</button></a>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="padding: 0%;">
                        <div id="doxe{{$loop->index}}" style="background: turquoise; color: white; display: none; padding: 10px">
                            <strong>Địa chỉ:</strong> {{$item['agencyaddress']}}
                            <br>
                            <strong>Hotline:</strong> {{$item['hotline']}}
                        </div>
                    </td>
                </tr>

                @endforeach
                @endisset

            </tbody>
        </table>
    </div>
    <div class="col-6">
        <h3 style="float: left;">Bảo Hiểm</h3>
        <button class="btn btn-info" style="float: right;" data-toggle="modal" data-target="#addAgencyModal" onclick="chuyenMien('baohiem')">Thêm</button>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Hành động</th>
                </tr>
            </thead>
            <tbody>
                @isset($baoHiem)
                @foreach ($baoHiem as $agency_id => $item)

                <tr>
                    <td>{{$loop->index + 1}}</td>
                    <td>{{$item["agencyname"]}}</td>
                    <td>
                        <button class="btn btn-info" onclick="showAgencyDetail('baohiem' , {{$loop->index}} , this)" style="width: 58px">Xem</button>
                        <a href="{{ route('deletePartner', ['mien' => 'baohiem' , 'agency_id'=>$agency_id]) }}"><button class="btn btn-danger">Xóa</button></a>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="padding: 0%;">
                        <div id="baohiem{{$loop->index}}" style="background: turquoise; color: white; display: none; padding: 10px">
                            <strong>Địa chỉ:</strong> {{$item['agencyaddress']}}
                            <br>
                            <strong>Hotline:</strong> {{$item['hotline']}}
                        </div>
                    </td>
                </tr>
                @endforeach
                @endisset

            </tbody>
        </table>
    </div>
  
</div>



@endsection

<script>
    function showAgencyDetail(mien , index , evt){
        $("#"+mien+index).slideToggle();
        $current = $(evt).html();
        if ($current == "Xem") {
            $(evt).html("Ẩn")
        }
        else {
            $(evt).html("Xem")
        }
    }

    function chuyenMien(customMien){
        $("#customMien").attr('value', customMien);
    }
</script>