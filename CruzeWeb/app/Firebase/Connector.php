<?php
namespace App\Firebase;
use Kreait\Firebase\Factory;
use Kreait\Firebase\ServiceAccount;
use Kreait\Firebase\Storage;

class Connector
{
    private $database;
    private $storage;
    public function __construct()
    {
        $factory = (new Factory)->withServiceAccount( app_path() . '\Firebase\Credentials' . '\cruzeclub-528da-firebase-adminsdk-wq70c-2cdd7583a2.json')->withDatabaseUri('https://cruzeclub-528da-default-rtdb.firebaseio.com/');
        $this->database = $factory->createDatabase();
        $this->storage = $factory->withDefaultStorageBucket('cruzeclub-528da.appspot.com')->createStorage();
    
    }

    public function DB()
    {
        return $this->database;
    }

    public function equalTo($rootKey , $orderKey , $equalVal){
        return array_values($this->DB()->getReference($rootKey)->orderByChild($orderKey)->equalTo($equalVal)->getSnapshot()->getValue());
    }

    public function search($rootKey , $orderKey , $equalVal){
        return array_values($this->DB()->getReference($rootKey)->orderByChild($orderKey)->startAt($equalVal)->endAt($equalVal . "\uf8ff")->getSnapshot()->getValue());
    }
    public function getAll($key){
        return $this->DB()->getReference($key)->getSnapshot()->getValue();
    }

    public function insert($key , $data){
        $this->DB()->getReference($key)->push($data);
    }

    public function createNew($key , $data){
        $this->DB()->getReference($key)->update($data);
    }

    public function delete($key){
        $this->DB()->getReference($key)->remove();
    }

    public function uploadFile($file , $filePath , $filename){
        $defaultBucket = $this->storage->getBucket();
        $object = $defaultBucket->upload(  $file,
        [
            'name' => $filename
        ]);

        unlink($filePath);
    }

    public function getImage($filePath){
        $defaultBucket = $this->storage->getBucket();
        $object = $defaultBucket->object($filePath);
        $image = $object->downloadAsStream();
        // return Image::make($image)->response();
        return response($image)->header('Content-type','image/png');
      
    }
}