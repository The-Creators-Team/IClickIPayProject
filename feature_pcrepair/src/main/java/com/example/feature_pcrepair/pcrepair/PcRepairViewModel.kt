package com.example.feature_pcrepair.pcrepair

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_pcrepair.R

class PcRepairViewModel : ViewModel(){

    private val _repairtechnicians = mutableStateListOf(
        PcRepairTechnician("Jessy Jones", "Johannesburg", 4.8, 500, 15, "Computer, Laptop, Mobile", R.drawable.jessie),
        PcRepairTechnician("Sarah Smith", "Cape Town", 4.7, 1200, 18, "Laptop, Mobile Repair", R.drawable.sarah),
        PcRepairTechnician("Peter Brown", "Pretoria", 4.9, 800, 20, "Networking, Servers", R.drawable.pctech),
        PcRepairTechnician("Emilia Davis", "Durban", 4.6, 600, 16, "Hardware, Software", R.drawable.sarah),
        PcRepairTechnician("David Wilson", "Port Elizabeth", 4.5, 1000, 14, "Laptop Repairs", R.drawable.pctech),
        PcRepairTechnician("Gloria White", "Bloemfontein", 4.8, 700, 17, "Mobile, Gadgets", R.drawable.sarah),
        PcRepairTechnician("Reth Johnson", "Johannesburg", 4.9, 400, 19, "Custom Builds", R.drawable.pctech),
        PcRepairTechnician("Mona Martinez", "Durban", 4.7, 500, 15, "General IT Support", R.drawable.sarah)

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

    private val _selectedDate = MutableLiveData<String>()
    var selectedDate: MutableLiveData<String> = _selectedDate

    private val _selectedTime = MutableLiveData<Any>()
    var selectedTime: MutableLiveData<Any> = _selectedTime

    private fun sortPcRepairTechsByRating(): List<PcRepairTechnician>{
        return _repairtechnicians.sortedByDescending { it.rating > _selectedRating.value!! }
    }

    val recommendByRating:List<PcRepairTechnician> get() = sortPcRepairTechsByRating()

    private fun sortPcRepairTechsByPrice(): List<PcRepairTechnician>{
        return _repairtechnicians.sortedByDescending { it.price <= _selectedPriceRange.value!! }
    }

    val recommendByPrice: List<PcRepairTechnician> get() = sortPcRepairTechsByPrice()

    private val _selectedPriceRange = MutableLiveData<Int>(60)
    var selectedPriceRange: MutableLiveData<Int> = _selectedPriceRange

    private val _selectedRating = MutableLiveData<Int>(1)
    var selectedRating: MutableLiveData<Int> = _selectedRating

    private val _isFiltered = MutableLiveData<Boolean>(false)
    var isFiltered: MutableLiveData<Boolean> = _isFiltered
}