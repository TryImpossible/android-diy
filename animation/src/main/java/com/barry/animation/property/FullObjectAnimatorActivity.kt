package com.barry.animation.property

import android.animation.ObjectAnimator
import android.animation.TypeConverter
import android.animation.TypeEvaluator
import android.graphics.Color
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Property
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.barry.animation.R

class FullObjectAnimatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_object_animator)

        val textview = findViewById<TextView>(R.id.tv_textview);
        val myTextView = findViewById<MyTextView>(R.id.tv_my_textview)

        findViewById<Button>(R.id.btn_rotation).setOnClickListener {
//            val animator = ObjectAnimator.ofFloat(textview, "rotation", 0f, 270f)
            val animator = ObjectAnimator.ofFloat(textview, View.ROTATION, 0f, 270f)
            animator.duration = 3000
            animator.repeatCount = -1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_translate_path).setOnClickListener {
            val path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(50f, 50f)
            path.lineTo(100f, 20f)
            path.lineTo(900f, 400f)
            path.lineTo(500f, 1000f)
            val animator = ObjectAnimator.ofFloat(textview, "translationX", "translationY", path)
            animator.duration = 3000
            animator.repeatCount = -1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_translate_scale_path).setOnClickListener {
            val path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(50f, 1f)
            path.lineTo(100f, 2f)
            path.lineTo(900f, 0.5f)
            path.lineTo(500f, 1f)
            val animator = ObjectAnimator.ofFloat(textview, "translationX", "scaleY", path)
            animator.duration = 3000
            animator.repeatCount = -1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_custom_property).setOnClickListener {
            class MyProperty(name: String) : Property<TextView, String>(String().javaClass, name) {
                override fun get(textview: TextView?): String {
                    return textview?.text.toString()
                }

                override fun set(`object`: TextView?, value: String?) {
                    `object`?.text = value
                }
            }

            class MyIntEvaluator : TypeEvaluator<String> {
                override fun evaluate(p0: Float, p1: String, p2: String): String {
                    val startInt = p1.toInt()
                    val endInt = p2.toInt()
                    val current = (startInt + p0 * (endInt - startInt)).toInt()
                    return current.toString()
                }
            }

            val myIntEvaluator = MyIntEvaluator()
            val myProperty = MyProperty("text")
            val animator = ObjectAnimator.ofObject(textview, myProperty, myIntEvaluator, "1", "10")
            animator.duration = 3000
            animator.repeatCount = -1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_ofArgb).setOnClickListener {
            val animator = ObjectAnimator.ofArgb(textview, "backgroundColor", Color.RED, Color.GREEN)
            animator.duration = 3000
            animator.repeatCount = 1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_custom_ofArgb).setOnClickListener {
            class MyProperty(name: String) : Property<TextView, Int>(Int::class.java, name) {
                override fun set(`object`: TextView, value: Int) {
                    `object`.setBackgroundColor(value)
                }

                override fun get(p0: TextView): Int {
                    val drawable = p0.background;
                    if (drawable is ColorDrawable) {
                        return drawable.color
                    }
                    return Color.YELLOW
                }
            }

            val animator = ObjectAnimator.ofArgb(textview, MyProperty("background"), Color.RED, Color.GREEN)
            animator.duration = 3000
            animator.repeatCount = 1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_ofMultiInt).setOnClickListener {
            val data = arrayOf(intArrayOf(1,9), intArrayOf(4,12))
            val animator = ObjectAnimator.ofMultiInt(myTextView, "muliText", data)
            animator.duration = 3000
            animator.repeatCount = 1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_ofMultiInt_path).setOnClickListener {
            val path = Path()
            path.moveTo(0f, 6f)
            path.lineTo(5f, 9f)
            val animator = ObjectAnimator.ofMultiInt(myTextView, "muliText", path)
            animator.duration = 3000
            animator.repeatCount = 1
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_ofMultiInt_converter_evaluator).setOnClickListener {
            class Point {
                var x = 0
                var y = 0
            }
//            class IntConverter(fromClass: Class<Point?>?, toClass: Class<IntArray?>?) : TypeConverter<Point?, IntArray?>(fromClass, toClass) {
//                override fun convert(value: Point?): IntArray? {
//                    return intArrayOf(value.x, value.y)
//                }
//            }
//            class PointEvaluator : TypeEvaluator<Point> {
//                override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
//                    val startxInt: Int = startValue.getX()
//                    val endxInt: Int = endValue.getX()
//                    val curx = (startxInt + fraction * (endxInt - startxInt)).toInt()
//                    val startyInt: Int = startValue.getY()
//                    val endyInt: Int = endValue.getY()
//                    val cury = (startyInt + fraction * (endyInt - startyInt)).toInt()
//                    val point = Point()
//                    point.setX(curx)
//                    point.setY(cury)
//                    return point
//                }
//            }

        }
    }
}