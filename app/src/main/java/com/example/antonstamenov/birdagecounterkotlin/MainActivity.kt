package com.example.antonstamenov.birdagecounterkotlin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.*
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.antonstamenov.birdagecounterkotlin.MainActivity.MyViewModel.Companion.AD_KEY
import com.example.antonstamenov.birdagecounterkotlin.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


const val KEY_ADULTS = "adults_key"
const val KEY_SUBA = "subadults_key"
const val KEY_IMM = "immatures_key"
const val KEY_JUV = "juveniles_key"

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    /**
     * initiate viewModel change observers
     */
    private val changeAdult =
        Observer<Int> { value -> value?.let { displayAdults(value)}
        }

    private val changeSub =
        Observer<Int> { value -> value?.let { displaySubadults(value)}
        }

    private val changeImm =
        Observer<Int> { value -> value?.let { displayImmatures(value)}
        }

    private val changeJuv =
        Observer<Int> { value -> value?.let { displayJuveniles(value)}
        }

    private val changeTotals =
        Observer<Int> { value -> value?.let { displayTotalCount(value)}
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.restoreState(savedInstanceState)
        viewModel.displayAdults.observe(this, changeAdult)
        viewModel.displaySubadults.observe(this, changeSub)
        viewModel.displayImmatures.observe(this, changeImm)
        viewModel.displayJuveniles.observe(this, changeJuv)
        viewModel.displayTotalCount.observe(this, changeTotals)
        lifecycle.addObserver(viewModel)
        minAd.setOnClickListener { viewModel.remAdult()}
        plusAd.setOnClickListener { viewModel.addAdult()}
        minSub.setOnClickListener { viewModel.remSubadult()}
        plusSub.setOnClickListener { viewModel.addSubadult()}
        immMin.setOnClickListener { viewModel.remImmatures()}
        plusImm.setOnClickListener { viewModel.addImmatures()}
        minJuv.setOnClickListener { viewModel.remJuveniles()}
        addJuv.setOnClickListener { viewModel.addJuveniles()}
        totals_button.setOnClickListener {viewModel.totalNumber()}
        reset_button.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)

            // Set the alert dialog title
            builder.setTitle("Do you wish to RESET the app?")

            // Display a message on alert dialog
            builder.setMessage("All data will be lost!")

            builder.setPositiveButton("Yes"){dialog, witch ->
                 finish(); startActivity(getIntent())
            }

            // Display a negative button on alert dialog
            builder.setNegativeButton("No"){dialog,which ->}

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }


    }

    /**
    * Displays the Adults numbers.
    */
    fun displayAdults(value: Int) {
        adult_count_text.text = (value).toString()
    }

    /**
     * Displays the Subadults numbers.
     */
    fun displaySubadults(numbers: Int) {
        sub_count_text.text = numbers.toString()
    }

    /**
     * Displays the Immatures numbers.
     */
    fun displayImmatures(numbers: Int) {
        imm_count_text.text = numbers.toString()
    }

    /**
     * Displays the Juveniles numbers.
     */
    fun displayJuveniles(numbers: Int) {
        juv_count_text.text = numbers.toString()
    }

    /**
     * Displays total numbers.
     */
    fun displayTotalCount(numbers: Int) {
        totals.text = numbers.toString()
    }

    fun displayTags(location: String) {
        val editText = findViewById<EditText>(R.id.editText)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var btn: ImageButton? = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveState(outState)
    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        Timber.i("onStart Called")

    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")

    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart Called")

    }

    /**
     * initiates viewModel for all counters
     */
    class MyViewModel (private var countAd: Int = 0, private var countSub: Int = 0, private var countImm: Int = 0,
                       private var countJuv: Int = 0) : ViewModel(), LifecycleObserver {

        /**
         * initiate saveInstance keys
         */
        companion object {
            const val AD_KEY = "AdKey"
            const val SUB_KEY = "SubKey"
            const val IMM_KEY = "ImmKey"
            const val JUV_KEY = "JuvKey"
        }

        val displayAdults = MutableLiveData<Int>()
        val displaySubadults = MutableLiveData<Int>()
        val displayImmatures = MutableLiveData<Int>()
        val displayJuveniles = MutableLiveData<Int>()
        val displayTotalCount = MutableLiveData<Int>()

        /**
         * Increase Adults by 1.
         */
        fun addAdult() { displayAdults.value = ++countAd
            timber.log.Timber.i("addAdult Called")
        }

        /**
         * Decrease Adults by 1.
         */
        fun remAdult() {
            if (countAd == 0) {
                return
            }
            displayAdults.value = --countAd
            Timber.i("remAdult Called")
        }

        /**
         * Increase Subdults by 1.
         */
        fun addSubadult() {
            displaySubadults.value = ++countSub
            Timber.i("addSubadult Called")
        }

        /**
         * Decrease Subdults by 1.
         */
        fun remSubadult() {
            if (countSub == 0) {
                return
            }
            displaySubadults.value = --countSub
            Timber.i("remSubadult Called")
        }

        /**
         * Increase Immatures by 1.
         */
        fun addImmatures() {
            displayImmatures.value = ++countImm
        }

        /**
         * Decrease Immatures by 1.
         */
        fun remImmatures() {
            if (countImm == 0) {
                return
            }
            displayImmatures.value = --countImm
        }

        /**
         * Increase Juveniles by 1.
         */
        fun addJuveniles() {
            displayJuveniles.value = ++countJuv
        }

        /**
         * Decrease Juveniles by 1.
         */
        fun remJuveniles() {
            if (countJuv == 0) {
                return
            }
            displayJuveniles.value = --countJuv
        }

        /**
         * Calcolate total numbers.
         */
        fun totalNumber() {
            displayTotalCount.value = countAd + countSub + countImm + countJuv
        }

        fun saveState(outState: Bundle) {
            outState.putInt(AD_KEY, countAd)
            outState.putInt(SUB_KEY, countSub)
            outState.putInt(IMM_KEY, countImm)
            outState.putInt(JUV_KEY, countJuv)
        }

        fun restoreState(inState: Bundle?) {
            inState?.let {
                countAd = inState.getInt(AD_KEY)
                countSub = inState.getInt(SUB_KEY)
                countImm = inState.getInt(IMM_KEY)
                countJuv = inState.getInt(JUV_KEY)
            }
        }

    }

}


