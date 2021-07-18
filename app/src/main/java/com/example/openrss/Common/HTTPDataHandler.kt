package com.example.openrss.Common

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.lang.System.`in`
import java.net.HttpURLConnection
import java.net.URL

public class HTTPDataHandler {
    var stream:String = ""

    public fun HTTPDataHandler(){}

    fun GetHTTPDataHandler(urlString: String): String? {
        try{
            val url = URL(urlString)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

            if(urlConnection.responseCode == HttpURLConnection.HTTP_OK){
                var inputStream: InputStream = BufferedInputStream(urlConnection.getInputStream())

                val r = BufferedReader(InputStreamReader(inputStream))
                var sb: StringBuilder = StringBuilder()
                var line: String = ""

                line = r.readLine()
                sb.append(line)
                stream = sb.toString()

                inputStream.close()
                urlConnection.disconnect()
            }
        }
        catch (ex:Exception){
            return null
        }
        return stream
    }
}