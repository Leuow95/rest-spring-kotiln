package br.com.leomaia.mapper.custom

import br.com.leomaia.data.vo.v2.PersonVO
import br.com.leomaia.model.Person
import org.springframework.stereotype.Service
import java.util.Date

@Service
class PersonMapper {

    fun mapEntityToVO(personEntity: Person): PersonVO{
        val vo = PersonVO()

        vo.id = personEntity.id
        vo.address = personEntity.address
        vo.birthDay = Date()
        vo.firstName = personEntity.firstName
        vo.lastName = personEntity.lastName
        vo.gender = personEntity.gender

        return vo
    }

    fun mapVoToEntity(vo: PersonVO): Person{
        val personEntity = Person()

        personEntity.id = vo.id
        personEntity.address = vo.address
//        personEntity.birthDay = Date()
        personEntity.firstName = vo.firstName
        personEntity.lastName = vo.lastName
        personEntity.gender = vo.gender

        return personEntity
    }
}