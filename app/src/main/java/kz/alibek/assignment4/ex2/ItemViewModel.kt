package kz.alibek.assignment4.ex2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.alibek.assignment4.ex2.data.Item
import kz.alibek.assignment4.ex2.repository.ItemRepository

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadItems() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val fetchedItems = repository.fetchItems()
                _items.value = fetchedItems
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
            }
        }
    }
}