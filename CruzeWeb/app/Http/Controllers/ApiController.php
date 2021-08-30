<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Firebase\Connector as FirebaseConnector;

class ApiController extends Controller
{
    private $firebase;
    public function __construct() {
        \Tinify\setKey("9sPDplXVKjkDLn0MWJtPjzzdQJFDwJdf");
        $this->firebase = new FirebaseConnector();
    }

    public function searchWithPlate($plate){
        echo json_encode($this->firebase->search("registered" , "plate" , $plate), JSON_UNESCAPED_UNICODE); 
    }

    public function getCarCodes(){
        echo json_encode(array_values($this->firebase->getAll("carcodes")), JSON_UNESCAPED_UNICODE); 
    }

    public function getAgencies($mien){
        echo json_encode(array_values($this->firebase->getAll("agency/".$mien)), JSON_UNESCAPED_UNICODE); 
    }

    public function getPartners($mien){
        echo json_encode(array_values($this->firebase->getAll("partner/".$mien)), JSON_UNESCAPED_UNICODE); 
    }

    public function getInfo(){
        echo $this->firebase->getAll("info"); 
    }

    public function allPlate(){
        echo json_encode(array_values($this->firebase->getAll("registered")),JSON_UNESCAPED_UNICODE); 
    }

    public function createTicket(Request $res){

        $filename = uniqid(rand(), true) . '.png';
        $path = tempnam(public_path(), 'timg');
        $sourceData = \Tinify\fromFile($res->file('plateimage'));
        $resized = $sourceData->resize(array(
            "method" => "scale",
            "width" => 766
        ))->toFile($path);
        $finalPath = str_replace(".tmp",".png",$path);
        var_dump($finalPath);


        rename($path, $finalPath);
        $file = file_get_contents($finalPath);
        $this->firebase->uploadFile($file , $finalPath , $filename);

 
        $this->firebase->insert("pnd_verify" , array_merge($res->except('_token' , 'plateimage'), ['fileName' => $filename]));
        echo $filename;
    }

}
