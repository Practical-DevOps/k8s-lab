package academy.softserve.guestbook

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface MessageService : PagingAndSortingRepository<Message, Long> {
}