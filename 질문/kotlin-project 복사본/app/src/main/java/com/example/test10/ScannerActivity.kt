package com.example.test10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class ScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // 여러가지 바코드중에 특정 바코드 설정 가능
        integrator.setPrompt("QR 코드를 스캔하여 주세요:)") // 스캔할 때 하단의 문구
        integrator.setCameraId(0) // 0은 후면 카메라, 1은 전면 카메라
        integrator.setBeepEnabled(true) // 바코드를 인식했을 때 삑 소리유무
        integrator.setBarcodeImageEnabled(false) // 스캔 했을 때 스캔한 이미지 사용여부
        integrator.initiateScan() // 스캔
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.d("TTT", "QR 코드 체크")
        val web_view = findViewById<WebView>(R.id.web_view)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                val preIntent = Intent(this, MainActivity::class.java)
                startActivity(preIntent)
            }
            else {
                Toast.makeText(this, "scanned" + result.contents, Toast.LENGTH_LONG).show()
                Log.d("TTT", "QR 코드 URL:${result.contents}")

                web_view.settings.javaScriptEnabled = true
                web_view.webViewClient = WebViewClient()

                web_view.loadUrl(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}