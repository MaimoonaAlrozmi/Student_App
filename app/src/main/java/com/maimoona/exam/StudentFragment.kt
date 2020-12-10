package com.maimoona.exam

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_fragment.*

private lateinit var btn: Button;

class StudentFragment : Fragment(), DialogAddNewStudent.Callbacks {

    private lateinit var noDataLinearayout:LinearLayout
    private lateinit var textView: TextView

    private val studentListViewModel: StudentsListViewModel by lazy {
        ViewModelProviders.of(this).get(StudentsListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val stu=studentListViewModel.studentList
        val view = inflater.inflate(R.layout.student_fragment, container, false)
        textView=view.findViewById(R.id.textView)
        printStudent(stu)
        return view;
    }

    fun printStudent(sList: List<Students>) {
        sList.forEach() {
            textView.append("${it.stdNo}|${it.stdNmae}|${it.stdPass}\n")
        }
    }

    override fun onStudentAdded(student: Students) {
        studentListViewModel.addStudent(student)
        studentListViewModel.updateStudent(student)
    }


    companion object {
        fun newInstance(): StudentFragment {
            return StudentFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.new_student -> {

                // studentListViewModel.addStudent(Students(UUID.randomUUID(),"Maimoona",2,true))
                //updateUI()
                DialogAddNewStudent().apply {
                    setTargetFragment(this@StudentFragment, 0)
                    show(this@StudentFragment.requireFragmentManager(), "Input")
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}