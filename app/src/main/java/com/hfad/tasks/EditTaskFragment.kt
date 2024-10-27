package com.hfad.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.tasks.databinding.FragmentEditTaskBinding

class EditTaskFragment : Fragment() {
    private var _binding: FragmentEditTaskBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root
        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao

        // Фабрика модели представления используется для получения
        // ссылки на модель представления
        val viewModelFactory = EditTaskViewModelFactory(taskId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditTaskViewModel::class.java)

        // Присваивается значение перемен ной связывания данных
        binding.viewModel = viewModel
        // Позволяет макету взаимодействовать со свойствами Live Data
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                // Переходим к TasksFragment, когда свойству navigateToList присваивается true,
                // и вызываем метод onNavigatedToList() модели представления
                view.findNavController().navigate(R.id.action_editTaskFragment_to_tasksFragment)
                viewModel.onNavigatedToList()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Отчистка ресурсов во избежания утечки памяти
        _binding = null
    }
}