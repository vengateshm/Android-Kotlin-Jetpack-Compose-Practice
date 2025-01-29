package dev.vengateshm.xml_kotlin.custom_views.flight_connection_gate_time_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.utils.bindOrHideWhenNull

class FlightConnectionGateTimeView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val parentMergeView: View

    private val viewConnectionTimeContainer: ConstraintLayout
    private val textViewConnectionTime: TextView
    private val imageViewLanding: ImageView
    private val imageViewTakeOff: ImageView
    private val imageViewTimeInfo: ImageView
    private val textViewWalkTimeDuration: TextView
    private val textViewWalkTimeLabel: TextView
    private val textViewArrivalGateLabel: TextView
    private val textViewDepartureGateLabel: TextView
    private val textViewArrivalGate: TextView
    private val textViewDepartureGate: TextView
    private val textViewArrivalTerminal: TextView
    private val textViewDepartureTerminal: TextView

    var infoIconClickListener: () -> Unit = {}
    var walkTimeClickListener: () -> Unit = {}

    init {
        parentMergeView =
            inflate(context, R.layout.common_ui_view_flight_connection_gate_time, this)
        viewConnectionTimeContainer = findViewById(R.id.common_ui_flightconnection_time_container)
        textViewConnectionTime = findViewById(R.id.common_ui_flightconnection_time_text)
        imageViewLanding = findViewById(R.id.common_ui_flightconnection_landing_imageview)
        imageViewTakeOff = findViewById(R.id.common_ui_flightconnection_takeoff_imageview)
        imageViewTimeInfo = findViewById(R.id.common_ui_flightconnection_time_info)
        textViewWalkTimeDuration =
            findViewById(R.id.common_ui_flightconnection_walk_time_duration_text)
        textViewWalkTimeLabel = findViewById(R.id.common_ui_flightconnection_walk_time_text)
        textViewArrivalGateLabel =
            findViewById(R.id.common_ui_flightconnection_arrival_gate_label_tv)
        textViewDepartureGateLabel =
            findViewById(R.id.common_ui_flightconnection_departure_gate_label_tv)
        textViewArrivalGate = findViewById(R.id.common_ui_flightconnection_arrival_gate)
        textViewDepartureGate = findViewById(R.id.common_ui_flightconnection_departure_gate)
        textViewArrivalTerminal = findViewById(R.id.common_ui_flightconnection_arrival_terminal_tv)
        textViewDepartureTerminal =
            findViewById(R.id.common_ui_flightconnection_departure_terminal_tv)
    }

    fun setData(data: FlightConnectionGateTimeData) {
        data.apply {
            if (pillViewTimeText.isEmpty()) {
                viewConnectionTimeContainer.visibility = View.GONE
            } else {
                viewConnectionTimeContainer.apply {
                    visibility = View.VISIBLE
                    setBackgroundResource(pillViewBackgroundRes)
                    setOnClickListener {
                        infoIconClickListener()
                    }
                }
                ContextCompat.getDrawable(context, R.drawable.common_ui_ic_alert_info)
                    ?.apply {
                        mutate()
                        setTint(ContextCompat.getColor(context, pillViewIconTintColor))
                        imageViewTimeInfo.setImageDrawable(this)
                    }
                textViewConnectionTime.apply {
                    bindOrHideWhenNull(pillViewTimeText)
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            pillViewTimeTextColor,
                        ),
                    )
                    contentDescription = pillViewTimeText
                }
            }

            if (walkTimeDurationText.isEmpty()) {
                findViewById<ConstraintLayout>(R.id.common_ui_flightconnection_walk_time_container)
                    .apply {
                        visibility = GONE
                    }
            } else {
                findViewById<ConstraintLayout>(R.id.common_ui_flightconnection_walk_time_container)
                    .apply {
                        visibility = VISIBLE
                        setOnClickListener {
                            walkTimeClickListener()
                        }
                    }
                textViewWalkTimeDuration.apply {
                    bindOrHideWhenNull(walkTimeDurationText)
                    contentDescription = walkTimeDurationText
                }
                textViewWalkTimeLabel.apply {
                    bindOrHideWhenNull(walkTimeLabelText)
                    contentDescription = walkTimeLabelText
                }
            }
            textViewArrivalGateLabel.apply {
                bindOrHideWhenNull(arrivalGateLabelText)
                contentDescription = context.getString(
                    R.string.common_ui_flight_connection_gate_content_desc,
                    arrivalGateLabelText,
                )
            }
            textViewDepartureGateLabel.apply {
                bindOrHideWhenNull(departureGateLabelText)
                contentDescription = context.getString(
                    R.string.common_ui_flight_connection_gate_content_desc,
                    departureGateLabelText,
                )
            }
            textViewArrivalGate.apply {
                bindOrHideWhenNull(arrivalGateValue)
                contentDescription = arrivalGateValue
            }
            textViewDepartureGate.apply {
                bindOrHideWhenNull(departureGateValue)
                contentDescription = departureGateValue
            }
            textViewArrivalTerminal.apply {
                bindOrHideWhenNull(arrivalTerminalText)
                contentDescription = arrivalTerminalText
            }
            textViewDepartureTerminal.apply {
                bindOrHideWhenNull(departureTerminalText)
                contentDescription = departureTerminalText
            }

            setContentDescription()
        }
    }

    private fun setContentDescription() {
        val headerContentDescription = imageViewLanding.contentDescription.toString().plus(", ")
            .plus(textViewConnectionTime.contentDescription().toString()).plus(", ")
            .plus(imageViewTimeInfo.contentDescription.toString()).plus(", ")
            .plus(imageViewTakeOff.contentDescription.toString()).plus(", ")
        val leftPartContentDescription =
            textViewArrivalGateLabel.contentDescription.toString().plus(", ")
                .plus(textViewArrivalGate.contentDescription().toString()).plus(", ")
                .plus(textViewArrivalTerminal.contentDescription().toString()).plus(", ")
        val middlePartContentDescription =
            textViewWalkTimeDuration.contentDescription().toString().plus(", ")
        val rightPartContentDescription =
            textViewDepartureGateLabel.contentDescription.toString().plus(", ")
                .plus(textViewDepartureGate.contentDescription().toString()).plus(", ")
                .plus(textViewDepartureTerminal.contentDescription().toString())
        parentMergeView.contentDescription =
            "$headerContentDescription$leftPartContentDescription$middlePartContentDescription$rightPartContentDescription"
    }

    private fun TextView.contentDescription(): CharSequence {
        return this.contentDescription?.takeIf { desc -> desc.isNotEmpty() }
            ?: this.text.takeIf { text -> text.isNotEmpty() } ?: ""
    }
}