package net.mohamadi.Data_Access.repository.file;


import net.mohamadi.Data_Access.entity.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
