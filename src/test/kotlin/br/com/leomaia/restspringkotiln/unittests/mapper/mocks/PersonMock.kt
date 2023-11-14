package br.com.leomaia.restspringkotiln.unittests.mapper.mocks

import br.com.leomaia.data.vo.v1.PersonVO
import br.com.leomaia.model.Person

class PersonMock {
    fun mockEntity(): Person {
        return mockEntity(0)
    }

    fun mockVO(): PersonVO{
        return mockVO(0)
    }

    fun mockEntity(number: Int): Person{
        val person = Person()
        person.id = number.toLong()
        person.firstName = "First Name Test $number"
        person.lastName = "Last Name Test $number"
        person.address = "Address Test $number"
        person.gender = if(number%2==0) "Male" else "Female"
        return person
    }

    fun mockEntityList(): java.util.ArrayList<Person> {
        val persons: java.util.ArrayList<Person> = java.util.ArrayList<Person>()
        for (i in 0..13) {
            persons.add(mockEntity(i))
        }
        return persons
    }

    fun mockVO(number: Int): PersonVO{
        val personVO = PersonVO()
        personVO.address = "Address Test $number"
        personVO.firstName = "First Name Test $number"
        personVO.gender = if(number%2==0) "Male" else "Female"
        personVO.id = number.toLong()
        personVO.lastName = "Last Name Test $number"
        return personVO
    }

    fun mockVOList(): ArrayList<PersonVO>{
        val persons: ArrayList<PersonVO> = ArrayList()
        for(i in 0..13){
            persons.add(mockVO(i))
        }
        return persons
    }


}