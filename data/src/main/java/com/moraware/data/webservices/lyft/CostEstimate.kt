package com.moraware.data.webservices.lyft

data class CostEstimate(val ride_type: String,
                        val estimated_duration_seconds: Int,
                        val estimated_distance_miles: Double,
                        val estimated_cost_cents_max: Int,
                        val primetime_percentage: String,
                        val currency: String,
                        val estimated_cost_cents_min: Int,
                        val display_name: String,
                        val primetime_confirmation_token: String,
                        val cost_token: String,
                        val is_valid_estimate: Boolean)