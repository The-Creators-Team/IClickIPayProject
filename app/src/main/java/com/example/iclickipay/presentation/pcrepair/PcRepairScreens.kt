package com.example.iclickipay.presentation.pcrepair

sealed class PcRepairScreens(val route: String){
    object PcRepairHomeScreen: PcRepairScreens("pc_repair_home_screen")
    object PcRepairProblemScreen: PcRepairScreens("pc_repair_problem_screen")
    object PcRepairOrderScreen: PcRepairScreens("pc_repair_order_screen")

    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}