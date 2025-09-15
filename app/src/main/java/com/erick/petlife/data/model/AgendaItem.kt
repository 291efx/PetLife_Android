data class AgendaItem(
    val id: String = "",
    val title: String = "",   // en agenda puede ser título del evento
    val description: String? = null,
    val date: com.google.firebase.Timestamp? = null,
    val ownerUid: String = "",
    val createdAt: com.google.firebase.Timestamp? = null,
    val updatedAt: com.google.firebase.Timestamp? = null
)
