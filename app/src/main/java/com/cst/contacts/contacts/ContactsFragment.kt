package com.cst.contacts.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contacts

class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    private lateinit var tagsRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tagsRV = view.findViewById(R.id.tagsRV)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        tagsRV.layoutManager = linearLayoutManager
        checkPermissionsAndGetContacts()
    }


    private fun displayContacts(contacts: List<ContactInfo>) {

        /** ==== თქვენი კოდი ==== **/
        tagsRV.adapter = TagsAdapter(contacts)
    }

    /** ======== ამის ქვევით კოდს არ შეეხოთ ============= **/

    private fun checkPermissionsAndGetContacts() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                123
            )
        } else {
            displayContacts(getContacts())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            displayContacts(getContacts())
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getContacts() =
        Contacts.getQuery().find().map {
            it.mapToContactInfo()
        }
}