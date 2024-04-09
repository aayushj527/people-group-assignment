package com.peoplegroup.assignmentapp.data.repository

import com.peoplegroup.assignmentapp.data.local.PersonDao
import com.peoplegroup.assignmentapp.data.local.PersonEntity
import com.peoplegroup.assignmentapp.data.mapper.toPersonEntity
import com.peoplegroup.assignmentapp.data.mapper.toPersonInfo
import com.peoplegroup.assignmentapp.data.remote.PersonService
import com.peoplegroup.assignmentapp.domain.model.PersonInfo
import com.peoplegroup.assignmentapp.domain.model.Status
import com.peoplegroup.assignmentapp.domain.repository.PersonRepository
import com.peoplegroup.assignmentapp.utilities.Constants
import com.peoplegroup.assignmentapp.utilities.NoConnectivityException
import com.peoplegroup.assignmentapp.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositoryImpl @Inject constructor(
    private val personDao: PersonDao,
    private val personService: PersonService
) : PersonRepository {

    /**
     *  To fetch person data from remote API.
     *
     *  @param limit Number of records to be fetched.
     */
    override suspend fun getPersonDataFromRemote(
        limit: Int?
    ): Flow<Resource<List<PersonInfo>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteData = try {
                val response =
                    personService.getAllPersons(results = limit ?: Constants.PERSON_DEFAULT_LIMIT)
                response.body()?.results?.map { it.toPersonEntity() }
            } catch (e: NoConnectivityException) {
                emit(Resource.Error(e.message))
                null
            } catch (e: Exception) {
                emit(Resource.Error("Couldn't load data"))
                null
            }

            emit(Resource.Success())

            /**
             *  If data fetched from server is non-null, then it will be stored in local DB.
             */
            remoteData?.let {
                insertPersonData(it)
            }

            emit(Resource.Loading(false))
        }
    }

    /**
     *  To get person list from local DB as a flow.
     */
    override fun getPersonDataFromLocal(): Flow<List<PersonInfo>> {
        return flow {
            personDao.getAllPersons().collect { personList ->
                emit(personList.map { it.toPersonInfo() })
            }
        }
    }

    /**
     *  To insert new records in person list.
     */
    override suspend fun insertPersonData(persons: List<PersonEntity>) =
        withContext(Dispatchers.IO) {
            personDao.insertUser(persons)
        }

    /**
     *  To update status(Accepted or Declined) of persons using their id(primary key).
     */
    override suspend fun updatePersonStatus(status: Status, id: Int) = withContext(Dispatchers.IO) {
        personDao.updatePersonStatus(status, id)
    }
}