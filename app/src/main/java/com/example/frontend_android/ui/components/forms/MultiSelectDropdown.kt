package com.example.frontend_android.ui.components.forms

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DropDownItemRender(
    item: DropDownItem,
    onRemoveClick: (DropDownItem) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(32.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
       Text(
           text = item.label,
           maxLines = 1,
           overflow = TextOverflow.Ellipsis
       )
       Icon(
           modifier = Modifier.clickable {
               onRemoveClick(item)
           },
           imageVector = Icons.Rounded.Close,
           contentDescription = "Supprimé"
       )
    }
}

data class DropDownItem (
    val value: String,
    val label: String,
)

@Composable
fun MultiSelectDropdown(
    label: String,
    scrollState: ScrollState,
    items: List<DropDownItem>,
    search: String,
    defaultSelectedItems: List<DropDownItem>,
    onSearchChange: (String) -> Unit,
    onClearClick: () -> Unit = {},
    onItemRemove: (DropDownItem) -> Unit = {},
    onItemAdded: (DropDownItem) -> Unit = {},
) {
    val windowInfo = LocalWindowInfo.current
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var scrollToPosition  by remember { mutableStateOf(0F) }
    var showClearButton by remember { mutableStateOf(false) }
    val selectedItemValues = remember {
        mutableStateListOf(*defaultSelectedItems.toTypedArray())
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        OutlinedTextField(
            value = search,
            onValueChange = onSearchChange,
            label = {
                Text(text = label)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    showClearButton = (focusState.isFocused)
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            snapshotFlow { windowInfo.isWindowFocused }.collect { isWindowFocused ->
                                if (isWindowFocused) {
                                    scrollState.animateScrollTo(scrollToPosition.roundToInt())
                                }
                            }
                        }
                    }
                },
            trailingIcon = {
                if (showClearButton) {
                    IconButton(onClick = {
                        onClearClick()
                    }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear")
                    }
                }
            },
        )
        Row(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            selectedItemValues.map { item ->
                DropDownItemRender(item) { p1 ->
                    onItemRemove(p1)
                    selectedItemValues.remove(p1)
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .height(200.dp)
                .onGloballyPositioned { coordinates ->
                    scrollToPosition = coordinates.positionInRoot().y + 200
                },
            state = lazyListState
        ) {
            if (items.isNotEmpty()) {
                items(items) { prediction ->
                    val alreadySelected = selectedItemValues.contains(prediction)
                    Row(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                if (!alreadySelected) {
                                    onItemAdded(prediction)
                                    selectedItemValues.add(prediction)
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (alreadySelected) Icon(imageVector = Icons.Rounded.Done, contentDescription = "already selected")
                            Text(text = prediction.label)
                        }

                    }


                }
            }

        }
    }




}


/*
var isExpanded by remember {
    mutableStateOf(false)
}

val selectedItems = remember {
    mutableStateListOf<String>()
}

var search by remember {
    mutableStateOf(TextFieldValue(""))
}
var category by remember { mutableStateOf("") }
var textFieldSize by remember { mutableStateOf(Zeo) }
val interactionSource = remember { MutableInteractionSource() }

@Composable
fun renderSelectedItems(items: List<String>) {
    for (item in items) {
        DropdownSelectedItem(item)
    }
}


Column(
modifier = Modifier
.fillMaxWidth()
.clickable(
interactionSource = interactionSource,
indication = null,
onClick = {
    expanded = false
}
)
) {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = "Animals",
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Medium
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AutoCompleteTextField(
                value = category,
                onValueChange = {
                    category = it
                    expanded = true
                },
                placeholder = "Enter any Animal Name",
                animals = animals,
                textFieldSize = { size -> textFieldSize = size }
            ) {
                expanded = !expanded
            }
        }

        AnimatedVisibility(visible = expanded) {
            AutoCompleteDropdown(
                modifier = Modifier.padding(horizontal = 5.dp),
                textFieldWidth = textFieldSize.width.dp,
                items = if (category.isNotEmpty()) {
                    animals.filter {
                        it.lowercase().contains(category.lowercase())
                    }.sorted()
                } else {
                    animals.sorted()
                }
            ) { item ->
                category = item
                expanded = false
            }
        }
    }
}

ExposedDropdownMenuBox(
expanded = isExpanded,
onExpandedChange = { isExpanded = it }
) {

    OutlinedTextField(
        prefix = { renderSelectedItems(selectedItems) },
        value = search,
        onValueChange = {
            onSearchChange(it.text)
            search = it
        },
        placeholder = {
            if (selectedItems.isEmpty()) {
                Text(text = "Sélectionnez une allergie")
            }
        },
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(),
        modifier = Modifier
            .fillMaxWidth() // Needed to anchor the dropdown menu
    )

    ExposedDropdownMenu(
        expanded = isExpanded && search.text.length >= 3,
        onDismissRequest = { isExpanded = false }
    ) {
        items.forEach { name ->
            AnimatedContent(
                targetState = selectedItems.contains(name),
                label = "Animate the selected item"
            ) { isSelected ->
                if (isSelected) {
                    DropdownMenuItem(
                        text = {
                            Text(text = name)
                        },
                        onClick = {
                            selectedItems.remove(name)
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Check,
                                contentDescription = null
                            )
                        }
                    )
                } else {
                    DropdownMenuItem(
                        text = {
                            Text(text = name)
                        },
                        onClick = {
                            search = TextFieldValue()
                            selectedItems.add(name)
                        },
                    )
                }
            }
        }
    }
} */