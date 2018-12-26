package com.example.antonstamenov.birdagecounterkotlin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

const val KEY_ADULTS = "adultNumbers"
const val KEY_SUBA = "subadNumbers"
const val KEY_IMM = "immNumbers"
const val KEY_JUV = "juvNumbers"

class MainActivity : AppCompatActivity() {

    // Number of adults counted
    private var adultNumbers: Int = 0

    // Number of subadults counted
    private var subadNumbers: Int = 0

    // Number of immatures counted
    private var immNumbers: Int = 0

    // Number of juveniles counted
    private var juvNumbers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            adultNumbers = savedInstanceState.getInt(KEY_ADULTS, 0)
            subadNumbers = savedInstanceState.getInt(KEY_SUBA, 0)
            immNumbers = savedInstanceState.getInt(KEY_IMM, 0)
            juvNumbers = savedInstanceState.getInt(KEY_JUV, 0)
        }
    }

    /**
     * Increase Adults by 1.
     */
    fun addAdult(v: View) {
        adultNumbers++
        displayAdults(adultNumbers)
    }

    /**
     * Decrease Adults by 1.
     */
    fun remAdult(v: View) {
        if (adultNumbers == 0) {
            Toast.makeText(
                applicationContext,
                "Negative counts restricted!", Toast.LENGTH_SHORT
            ).show()
            return
        }
        adultNumbers--
        displayAdults(adultNumbers)
    }

    /**
     * Increase Subdults by 1.
     */
    fun addSubadult(v: View) {
        subadNumbers++
        displaySubadults(subadNumbers)
    }

    /**
     * Decrease Subdults by 1.
     */
    fun remSubadult(v: View) {
        if (subadNumbers == 0) {
            Toast.makeText(
                applicationContext,
                "Negative counts restricted!", Toast.LENGTH_SHORT
            ).show()
            return
        }
        subadNumbers--
        displaySubadults(subadNumbers)
    }

    /**
     * Increase Immatures by 1.
     */
    fun addImmatures(v: View) {
        immNumbers++
        displayImmatures(immNumbers)
    }

    /**
     * Decrease Immatures by 1.
     */
    fun remImmatures(v: View) {
        if (immNumbers == 0) {
            Toast.makeText(
                applicationContext,
                "Negative counts restricted!", Toast.LENGTH_SHORT
            ).show()
            return
        }
        immNumbers--
        displayImmatures(immNumbers)
    }

    /**
     * Increase Juveniles by 1.
     */
    fun addJuveniles(view: View) {
        juvNumbers++
        displayJuveniles(juvNumbers)
    }

    /**
     * Decrease Juveniles by 1.
     */
    fun remJuveniles(v: View) {
        if (juvNumbers == 0) {
            Toast.makeText(
                applicationContext,
                "Negative counts restricted!", Toast.LENGTH_SHORT
            ).show()
            return
        }
        juvNumbers--
        displayJuveniles(juvNumbers)
    }

    /**
     * Calcolate total numbers.
     */
    fun totalNumber(v: View) {
        val totalCount = (juvNumbers + immNumbers + subadNumbers
                + adultNumbers)
        displayTotalCount(totalCount)
    }


    /**
     * Displays the Adults numbers.
     */
    fun displayAdults(numbers: Int) {
        val scoreView = findViewById<View>(R.id.adult) as TextView
        scoreView.text = numbers.toString()
    }

    /**
     * Displays the Subadults numbers.
     */
    fun displaySubadults(numbers: Int) {
        val scoreView = findViewById<View>(R.id.countSub) as TextView
        scoreView.text = numbers.toString()
    }

    /**
     * Displays the Immatures numbers.
     */
    fun displayImmatures(numbers: Int) {
        val scoreView = findViewById<View>(R.id.countImm) as TextView
        scoreView.text = numbers.toString()
    }

    /**
     * Displays the Juveniles numbers.
     */
    fun displayJuveniles(numbers: Int) {
        val scoreView = findViewById<View>(R.id.countJuv) as TextView
        scoreView.text = numbers.toString()
    }

    /**
     * Displays total numbers.
     */
    fun displayTotalCount(numbers: Int) {
        val scoreView = findViewById<View>(R.id.totals) as TextView
        scoreView.text = numbers.toString()
    }

    fun displayTags(location: String) {
        val editText = findViewById<EditText>(R.id.editText)
    }

    /**
     * Reset count.
     */

    fun res(view: View) {
        val altdial = AlertDialog.Builder(this@MainActivity)
        altdial.setMessage(" ").setCancelable(false)
            .setPositiveButton("Yes") { dialogInterface, i ->
                adultNumbers = 0
                subadNumbers = 0
                immNumbers = 0
                juvNumbers = 0
                val totalCount = 0
                displayAdults(adultNumbers)
                displaySubadults(subadNumbers)
                displayImmatures(immNumbers)
                displayJuveniles(juvNumbers)
                displayTotalCount(totalCount)
            }
            .setNegativeButton("No") { dialogInterface, i -> dialogInterface.cancel() }
        val alert = altdial.create()
        alert.setTitle("Reset")
        alert.show()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var btn: ImageButton? = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_ADULTS, adultNumbers)
        outState.putInt(KEY_SUBA, subadNumbers)
        outState.putInt(KEY_IMM, immNumbers)
        outState.putInt(KEY_JUV, juvNumbers)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        adultNumbers = savedInstanceState!!.getInt(KEY_ADULTS)
        subadNumbers = savedInstanceState!!.getInt(KEY_SUBA)
        immNumbers = savedInstanceState!!.getInt(KEY_IMM)
        juvNumbers = savedInstanceState!!.getInt(KEY_JUV)

    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onRestart() {
        super.onRestart()

    }

}


