
# Android application for advertizing products

This is android application developped with android framwork in kotlin,Libgdx framework and firebase for backend.To advertize A company products. 


## Acknowledgements

 - [AVIONAV](https://avionav.net/)
 - [android developper](https://developer.android.com/)
 - [stack over flow](https://stackoverflow.com/)
 - [firebasedocumentaion](https://firebase.google.com/docs/auth/android/start)
 - [meduim](https://medium.com/)


## Documentation

In this section we going to specify our some project stucture.

First Firebase Realtime database structure:

PlaneIntro{
    Product_Id{
        desciption:"Product desciption",
        id:"Product_Id",
        imageURl:"Url of the prduct from fire base storage",
        name:"product name"
    }
}

To change PlaneIntro for another product change it in the Realtime database firs then in
app/src/main/java/com/example/avionav/viewModel/HomeViewModel.kt 
in planeSnapshot properties change PlaneIntro in the string too.

Then the Firebase Storage stucture:

file structure:

plane/Product_Id/productName the file stucture dont affect your application so your free to change it how you like it .

To get the imageURl: Click on image in the Firebase storage go to file location then Click on the access token to copy the image download url.

Product 3D model file structure :

Add them to asset under a file with Product_Id as it is name.
## Demo

In the screnshot file you'll find the demo for this app.


## Installation

To Install the project

First create your project in firebase console and choose android as the the platform your applicatio will support.

Then follow the [guidance](https://console.firebase.google.com/)

Then Add your data your data to the firebase Realtime database as the documentation stactured.
Don't forget to specify your rule for your database [Security](https://firebase.google.com/docs/database/security)

Then add the images of the corresponding product to firebase storage as the documentation disciption

Then Add the product 3d model in g3db fo [conversion](https://github.com/libgdx/fbx-conv)

Lastely run your application via your favorite ide(Android studion recommended).