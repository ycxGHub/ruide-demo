package com.ruide.robot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ruide.aidl.func.ITMCService
import com.ruide.aidl.para.TMC
import com.ruide.lib.IMLibClient

class MainActivity : AppCompatActivity() {
    lateinit var tvHa: TextView
    lateinit var tvVa: TextView
    lateinit var tvDis: TextView
    lateinit var btnMea: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val func = IMLibClient.getInstance().buildClientFunc(ITMCService::class.java)
        tvHa = findViewById(R.id.tv_ha)
        tvVa = findViewById(R.id.tv_va)
        btnMea = findViewById(R.id.btn_meadis)
        tvDis = findViewById(R.id.tv_dis)
        btnMea.setOnClickListener {

            Thread {
                //测量前确定合作目标需要在用户程序中设置
                //启动测量目前仅支持TMC_DEF_DIST
                val meaResponse =
                    func.doMeasure(TMC.MeasurePrg.TMC_DEF_DIST, TMC.InclinePrg.TMC_AUTO_INC);
                if (meaResponse.isSuccess) {
                    //读取距离
                    val distanceResponse = func.getSimpleMea(1000, TMC.InclinePrg.TMC_AUTO_INC)
                    if (distanceResponse.isSuccess) {
                        runOnUiThread {
                            tvDis.text = distanceResponse.data.dSlopeDistance.toString()
                        }
                    }
                    //停止测量
                    func.doMeasure(TMC.MeasurePrg.TMC_CLEAR, TMC.InclinePrg.TMC_AUTO_INC)
                }
            }.start()
        }

        Thread {
            while (true) {
                val response = func.getAngle1(TMC.InclinePrg.TMC_AUTO_INC)//阻塞
                if (response.isSuccess) {
                    val angleHa = response.data.dHz
                    val angleVa = response.data.dV
                    runOnUiThread(Runnable {
                        tvHa.text = "HA:$angleHa"
                        tvVa.text = "VA:$angleVa"
                    })
                }
                Thread.sleep(100)
            }
        }.start()
    }


}