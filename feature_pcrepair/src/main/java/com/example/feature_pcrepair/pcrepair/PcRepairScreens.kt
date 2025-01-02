package com.example.feature_pcrepair.pcrepair

sealed class PcRepairScreens(val route: String){
    object PcRepairHomeScreen: PcRepairScreens("pc_repair_home_screen")
    object PcRepairProblemScreen: PcRepairScreens("pc_repair_problem_screen")
    object PcRepairOrderScreen: PcRepairScreens("pc_repair_order_screen")
    object PcRepairAppointmentPickerScreen: PcRepairScreens("pc_repair_appointment_picker_screen")
    object PcRepairFilterScreen: PcRepairScreens("pc_repair_filter_screen")
    object PcRepairSearchListScreen: PcRepairScreens("pc_repair_search_list_screen")

    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}