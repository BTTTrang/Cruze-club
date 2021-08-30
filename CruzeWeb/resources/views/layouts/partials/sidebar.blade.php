<div class="cz-sidebar d-flex flex-column flex-shrink-0 p-3 bg-white shadow-lg p-3 mb-5 rounded">
    <a>Cruze Club Dashboard</a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="{{ route('index') }}" class="nav-link {{ Request::is('/') ? 'active' : ''}}" aria-current="page">
                Duyệt đăng ký
            </a>
        </li>
     
        <li>
            <a href="{{ route('registered') }}" class="nav-link {{ Request::is('registered') ? 'active' : ''}}">
                Xem danh sách đăng ký
            </a>
        </li>
        <li>
            <a href="{{ route('carCodes') }}" class="nav-link {{ Request::is('car_codes') ? 'active' : ''}}">
                Quản lý code
            </a>
        </li>
        <li>
            <a href="{{ route('agency') }}" class="nav-link {{ Request::is('agency') ? 'active' : ''}}">
                Quản lý đại lý
            </a>
        </li>
        <li>
            <a href="{{ route('partner') }}" class="nav-link {{ Request::is('partner') ? 'active' : ''}}">
                Quản lý bạn đồng hành
            </a>
        </li>

        <li>
            <a href="{{ route('info') }}" class="nav-link {{ Request::is('info') ? 'active' : ''}}">
                Quản lý giới thiệu
            </a>
        </li>
        <li>
            <a href="{{ route('logout') }}" class="nav-link">
                Đăng xuất
            </a>
        </li>
    </ul>

</div>