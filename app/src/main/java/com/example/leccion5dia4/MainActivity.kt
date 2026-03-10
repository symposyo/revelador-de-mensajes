package com.example.leccion5dia4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.leccion5dia4.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //Variable de ViewBinding
    // lateinit significa que la inicializaremos después
    private lateinit var  binding : ActivityMainBinding

    //Lista donde guardaremos todos los mensajes leídos desde el archivo txt
    private val listaMensajes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflamos el layouyt usando ViewBinding
        //esto crea el objeto que conecta el XML con KOTLIN
        binding = ActivityMainBinding.inflate(layoutInflater)
        //le vamos a indicar a Android que nos muester el layout
        setContentView(binding.root)
        //Cargar mensajes desde ela rchivo dentro de assets
        //Esto se ejcuta una sola vez al iniciar la app
        cargarMensajes()
        binding.btnMensaje.setOnClickListener {

            //obtener un mensaje aleaatorio
            val mensaje = obtenerMensajeAleatorio()
            //Mostramos el mensaje en el TextView
            binding.tvMensaje.text=mensaje

        }

    }

    private fun cargarMensajes(){
        try{
            //Obtenermos el AssetManager
            //AssetsManager es el encargado de acceder a los archivos dentro de assets
            val assetManager = assets

            //Abrirmos el archivo mensajes.txt
            //Esto devuelve un flujo de datos de entrada (InputStream)
            val inputStream = assetManager.open("mensajes.txt")
            //creamos un lector para poder leer ela rchivo linea por linea
            val reader = BufferedReader(InputStreamReader(inputStream))
            //Variable para almacenar cada linea que leamos

            var linea:String?
        while(reader.readLine().also { linea=it}!=null ){
            //agregamos cada linea a la lissta de mensajes
           listaMensajes.add(linea!!)
        }
            //cerrar el lector para liberar la memoria
            reader.close()
        }catch(e: Exception){
            //si ocurre algun error al lear el archivo mostramos un mensaje en pantalla
            binding.tvMensaje.text="Error leyendo mensajes"
        }
    }
    private fun obtenerMensajeAleatorio():String{
        //Si la lista esta vacia devolvemos un mensaje de advertencia
        if(listaMensajes.isEmpty()){
            return "No haya mensajes disponibles"
        }
        //Geeneramos un numero aletario entre 0 y el tamaño de la lista
        val indice = Random.nextInt(listaMensajes.size)
        //Retornarmos el mensaje que estsa en la psociion
        return listaMensajes[indice]
    }
    }
