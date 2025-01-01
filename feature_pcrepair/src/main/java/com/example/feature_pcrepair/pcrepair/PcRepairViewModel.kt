package com.example.feature_pcrepair.pcrepair

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PcRepairViewModel : ViewModel(){

    private val _repairtechnicians = mutableStateListOf(
        PcRepairTechnician("Jessy Jones", "Johannesburg", 4.8, 500, 15, "Computer, Laptop, Mobile"),
        PcRepairTechnician("Sarah Smith", "Cape Town", 4.7, 1200, 18, "Laptop, Mobile Repair"),
        PcRepairTechnician("Michael Brown", "Pretoria", 4.9, 800, 20, "Networking, Servers"),
        PcRepairTechnician("Emily Davis", "Durban", 4.6, 600, 16, "Hardware, Software"),
        PcRepairTechnician("David Wilson", "Port Elizabeth", 4.5, 1000, 14, "Laptop Repairs"),
        PcRepairTechnician("Sophia White", "Bloemfontein", 4.8, 700, 17, "Mobile, Gadgets"),
        PcRepairTechnician("Chris Johnson", "Johannesburg", 4.9, 400, 19, "Custom Builds"),
        PcRepairTechnician("Lisa Martinez", "Durban", 4.7, 500, 15, "General IT Support")

    )
    private val _selectedRepairTechnician = MutableLiveData<PcRepairTechnician>()
    var selectedRepairTechnician: MutableLiveData<PcRepairTechnician> = _selectedRepairTechnician

     val repairTechnicians: List<PcRepairTechnician> get() = _repairtechnicians

    private val _selectedType = MutableLiveData<String>("Laptop")
    var selectedType: MutableLiveData<String> = _selectedType

    private val _selectedProblem = MutableLiveData<String>("Do not work")
    var selectedProblem: MutableLiveData<String> = _selectedProblem

    private val _selectedAvailability = MutableLiveData<String>("14")
    var selectedAvailability: MutableLiveData<String> = _selectedAvailability

    private val _selectedDate = MutableLiveData<Any>()
    var selectedDate: MutableLiveData<Any> = _selectedDate
}