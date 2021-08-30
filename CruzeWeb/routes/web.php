<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\LoginController as login;
use App\Http\Controllers\CruzeController as cruze;
use App\Http\Controllers\ApiController as api;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/




Route::prefix('/')->middleware("login")->group(function () {
    Route::get('/', [cruze::class , "index"])->name("index");
    Route::get('/registered', [cruze::class , "registered"])->name("registered");
    Route::post('/create_ticket' , [cruze::class , 'createTicket'])->name("createTicket");
    Route::get('/accept_ticket/{ticket}' , [cruze::class , 'acceptTicket'])->name("acceptTicket");
    Route::get('/deny_ticket/{ticket}' , [cruze::class , 'denyTicket'])->name("denyTicket");
    Route::get('/delete_verified_ticket/{ticket}' , [cruze::class , 'deleteVerifiedTicket'])->name("deleteVerifiedTicket");
    Route::get('/car_codes' , [cruze::class , 'carCodes'])->name("carCodes");
    Route::post('/car_codes' , [cruze::class , 'createCarCode'])->name("createCarCode");
    Route::get('/delete_car_code/{car_code}' , [cruze::class , 'deleteCarCode'])->name("deleteCarCode");
    Route::get('/agency' , [cruze::class , 'agency'])->name("agency");
    Route::post('/agency' , [cruze::class , 'postAgency'])->name("postAgency");
    Route::get('/delete_agency/{mien}/{agency_id}' , [cruze::class , 'deleteAgency'])->name("deleteAgency");
    Route::get('/partner' , [cruze::class , 'partner'])->name("partner");
    Route::post('/partner' , [cruze::class , 'postPartner'])->name("postPartner");
    Route::get('/delete_partner/{mien}/{agency_id}' , [cruze::class , 'deletePartner'])->name("deletePartner");
    Route::get('/info' , [cruze::class , 'info'])->name("info");
    Route::post('/info' , [cruze::class , 'postInfo'])->name("postInfo");

});
Route::get('testshow', function () {
  
});
Route::get('/image/{filePath}', [cruze::class , 'getImage']);
Route::post('/uploadImage' , [cruze::class , 'uploadImage'])->name("uploadImage");
Route::prefix('/api')->group(function () {
    Route::post('create_ticket', [api::class , "createTicket"])->withoutMiddleware([\App\Http\Middleware\VerifyCsrfToken::class]);
    Route::get('allplate' , [api::class , 'allPlate']);
    Route::get('search/{plate}', [api::class , 'searchWithPlate']);
    Route::get('car_codes', [api::class , 'getCarCodes']);
    Route::get('agency/{mien}', [api::class , 'getAgencies']);
    Route::get('partner/{mien}', [api::class , 'getPartners']);
    Route::get('info', [api::class , 'getInfo']);
});

Route::get('/login', function () {
    return view("login");
})->name("login");

Route::get('/logout' , [login::class , 'logout'])->name("logout");
Route::post('login', [login::class , 'postLogin'])->name("postLogin");




