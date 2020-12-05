package com.barry.animation.property

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
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
            val data = arrayOf(intArrayOf(1, 9), intArrayOf(4, 12))
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
        findViewById<Button>(R.id.btn_ofObject).setOnClickListener {
            class Point {
                var x = 0
                var y = 0

            }

            class MyProperty2(name: String?) : Property<TextView, Point>(Point::class.java, name) {
                override fun get(p0: TextView): Point {
                    val point = Point()
                    point.x = p0.translationX.toInt()
                    point.y = p0.translationY.toInt()
                    return point
                }

                override fun set(`object`: TextView, value: Point) {
                    `object`.translationX = value.x.toFloat()
                    `object`.translationY = value.y.toFloat()
                }
            }

            class PointEvaluator : TypeEvaluator<Point> {
                override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
                    val startxInt: Int = startValue.x
                    val endxInt: Int = endValue.x
                    val curx = (startxInt + fraction * (endxInt - startxInt)).toInt()
                    val startyInt: Int = startValue.y
                    val endyInt: Int = endValue.y
                    val cury = (startyInt + fraction * (endyInt - startyInt)).toInt()
                    val point = Point()
                    point.x = curx
                    point.y = cury
                    return point
                }
            }

            val property2 = MyProperty2("tran") // 参数只是为了标识无具体意义
            val evaluator = PointEvaluator()
            val point1 = Point()
            point1.x = 0
            point1.y = 100

            val point2 = Point()
            point2.x = 700
            point2.y = 1000

            val animator = ObjectAnimator.ofObject(textview, property2, evaluator, point1, point2)
            animator.duration = 3000
            animator.interpolator = LinearInterpolator()
            animator.repeatCount = -1
            animator.start()
        }

        findViewById<Button>(R.id.btn_ofPropertyValuesHolder).setOnClickListener {
            val k0 = Keyframe.ofFloat(.0f, .0f)
            val k1 = Keyframe.ofFloat(.5f, 360f)
            val k2 = Keyframe.ofFloat(1f, .0f)
            val pvhRotation = PropertyValuesHolder.ofKeyframe(View.ROTATION, k0, k1, k2)
            val animator = ObjectAnimator.ofPropertyValuesHolder(textview, pvhRotation)
            animator.duration = 3000
            animator.start()
        }
    }
}