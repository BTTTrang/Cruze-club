<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Firebase\Connector as FirebaseConnector;

class LoginController extends Controller
{
    private $firebase;
    public function __construct() {
        $this->firebase = new FirebaseConnector();
    }
    public function postLogin(Request $res){
        $user_object = $this->firebase->equalTo("accounts" , "username" , $res->username);
        if ($user_object != null) {
            if ($user_object[0]["password"] == $res->password) {
                $res->session()->put('logged', "1");
                return redirect()->route("index");
            }  
            else {
                echo "Mật khẩu không đúng";
            }
        }
        else {
            echo "Tài khoản không tồn tại";
        }
    }

    public function logout(Request $res){
        $res->session()->flush();
        return redirect()->route('login');
    }
}
