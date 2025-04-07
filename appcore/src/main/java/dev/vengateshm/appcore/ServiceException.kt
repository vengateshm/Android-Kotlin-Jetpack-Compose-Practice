package dev.vengateshm.appcore

import dev.vengateshm.appcore.utility.StringWrapper
import dev.vengateshm.appcore.utility.asStringWrapper

data class ServiceException(
    val code: String?,
    val string: StringWrapper,
    val type: Any? = null,
    val title: StringWrapper? = null,
) {
    constructor(
        code: String?,
        string: String,
        type: Any? = null,
        title: String? = null,
    ) : this(code, string.asStringWrapper(), type, title?.asStringWrapper())
}
