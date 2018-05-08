package com.example.carlos.administraciondecuentas;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

public class NuevoProductoActivity extends AppCompatActivity{
    DataHandler dataHandler;
    ImageView imagen;
    EditText nombre,codigo,cantidad,precioVenta,precioCompra;
    String filePath ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
        dataHandler = getIntent().getParcelableExtra("dataHandler");
        nombre = findViewById(R.id.NuevoProductoEditTxtName);
        codigo = findViewById(R.id.NuevoProductoEditTxtCode);
        cantidad = findViewById(R.id.NuevoProductoEditTxtCant);
        precioCompra = findViewById(R.id.NuevoProductoEditTxtPC);
        precioVenta = findViewById(R.id.NuevoProductoEditTxtPV);
        imagen = findViewById(R.id.NuevoProductoImage);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 2);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    filePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap imagenSelected = BitmapFactory.decodeFile(filePath);
                    imagen.setImageBitmap(imagenSelected);
                }
        }
    }

    public void GuardarProducto(View v){
        if(!nombre.getText().toString().equals("")){
            if(!codigo.getText().toString().equals("")) {
                if (!cantidad.getText().toString().equals("")) {
                    if (!precioVenta.getText().toString().equals("")) {
                        if (!precioCompra.getText().toString().equals("")) {
                            if (!filePath.equals("")) {
                                dataHandler.getInventario().add(new Producto(codigo.getText().toString(),
                                        nombre.getText().toString(),
                                        Float.parseFloat(precioVenta.getText().toString()),
                                        Float.parseFloat(precioCompra.getText().toString()),
                                        Integer.parseInt(cantidad.getText().toString()),
                                        filePath));
                                Toast.makeText(this,"Se ingreso el producto",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Por favor seleccione una imagen", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this,"Por favor ingrese Precio de compra",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this,"Ingrese Precio de Venta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"Ingrese cantidad por favor",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Por favor ingrese codigo",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Por favor Ingrese un nombre",Toast.LENGTH_SHORT).show();
        }

    }

    public void CancelarProducto(View v){
        nombre.setText("");
        cantidad.setText("");
        precioCompra.setText("");
        precioVenta.setText("");
        codigo.setText("");
    }

    @Override
    public void onBackPressed(){
        //retornando nuevo dataHandler
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",dataHandler);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        super.onBackPressed();
    }
}
