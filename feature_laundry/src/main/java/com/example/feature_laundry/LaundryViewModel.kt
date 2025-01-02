package com.example.feature_laundry

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LaundryViewModel : ViewModel(){
    private val _laundryProfessionals = mutableStateListOf(
        LaundryProfessional("Jessy Jones", "Johannesburg", 4.8, 500, 15, "Computer, Laptop, Mobile", R.drawable.jessie),
        LaundryProfessional("Sarah Smith", "Cape Town", 4.7, 1200, 18, "Laptop, Mobile Repair", R.drawable.sarah),
        LaundryProfessional("Michael Brown", "Pretoria", 4.9, 800, 20, "Networking, Servers", R.drawable.pctech),
        LaundryProfessional("Emily Davis", "Durban", 4.6, 600, 16, "Hardware, Software", R.drawable.jessie),
        LaundryProfessional("David Wilson", "Port Elizabeth", 4.5, 1000, 14, "Laptop Repairs", R.drawable.sarah),
        LaundryProfessional("Sophia White", "Bloemfontein", 4.8, 700, 17, "Mobile, Gadgets", R.drawable.jessie),
        LaundryProfessional("Chris Johnson", "Johannesburg", 4.9, 400, 19, "Custom Builds", R.drawable.pctech),
        LaundryProfessional("Lisa Martinez", "Durban", 4.7, 500, 15, "General IT Support", R.drawable.sarah)

    )

    val laundryProfessionals: List<LaundryProfessional> get() = _laundryProfessionals

    private val _selectedPriceRange = MutableLiveData<Int>(60)
    var selectedPriceRange: MutableLiveData<Int> = _selectedPriceRange

    private val _selectedRating = MutableLiveData<Int>(1)
    var selectedRating: MutableLiveData<Int> = _selectedRating

    private val _isFiltered = MutableLiveData<Boolean>(false)
    var isFiltered: MutableLiveData<Boolean> = _isFiltered

    private val _selectedType = MutableLiveData<String>("5")
    var selectedType: MutableLiveData<String> = _selectedType

    private val _selectedProblem = MutableLiveData<String>("2")
    var selectedProblem: MutableLiveData<String> = _selectedProblem

    private val _requiresIroning = MutableLiveData<Boolean>(true)
    var requiresIroning: MutableLiveData<Boolean> = _requiresIroning

    private val _selectedAvailability = MutableLiveData<String>("14")
    var selectedAvailability: MutableLiveData<String> = _selectedAvailability

    private val _selectedDate = MutableLiveData<Any>()
    var selectedDate: MutableLiveData<Any> = _selectedDate

    private val _selectedTime = MutableLiveData<Any>()
    var selectedTime: MutableLiveData<Any> = _selectedTime

    private val _selectedLaundryProfessional = MutableLiveData<LaundryProfessional>()
    var selectedLaundryProfessional: MutableLiveData<LaundryProfessional> = _selectedLaundryProfessional

}