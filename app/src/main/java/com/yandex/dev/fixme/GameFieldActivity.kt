package com.yandex.dev.fixme

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.yandex.dev.fixme.base.BaseItem
import com.yandex.dev.fixme.controller.Controller
import com.yandex.dev.fixme.controller.ViewController
import kotlinx.android.synthetic.main.activity_game_field.*

class GameFieldActivity : AppCompatActivity(), ViewController {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var controller: Controller
    private val hearts = findViewById<LinearLayout>(R.id.hearts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_field)

        val items: List<BaseItem> = listOf<ImageView>(
                item1,
                item2,
                item3,
                item4,
                item5,
                item6,
                item7,
                item8,
                item9
        ).map { BaseItem(it) }
        controller = Controller(items)
        controller.startGame()
    }

    override fun updateScore(score: Int) {
        handler.post {
            bugFixed.text = getString(R.string.bugFixed).replace("0", score.toString())
        }
    }

    override fun updateLife(life: Int) {
        handler.post {
            hearts.getChildAt(hearts.childCount - life - 1).visibility = View.INVISIBLE
        }
    }

    override fun exit(currentScore: Int) {
        handler.post {
            finalScore.text = "${getString(R.string.finalScore)} $currentScore"
        }
    }
}
