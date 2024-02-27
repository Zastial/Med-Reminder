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
private fun DropDownSelectedItemRenderer(
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
                DropDownSelectedItemRenderer(item) { p1 ->
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
