package ru.keixd.base.navigation.entity

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

interface INavArgs : Parcelable

@Immutable
@Parcelize
object NoNavArgs: INavArgs