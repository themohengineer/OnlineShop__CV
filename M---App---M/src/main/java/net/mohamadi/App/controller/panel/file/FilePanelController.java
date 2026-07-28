package net.mohamadi.App.controller.panel.file;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.DeleteController;
import net.mohamadi.App.controller.base.ReadController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.file.FileService;
import net.mohamadi.dto.file.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/file")
public class FilePanelController implements
        ReadController<FileDto>,
        DeleteController<FileDto> {


    private final FileService service;

    @Autowired
    public FilePanelController(FileService service) {
        this.service = service;
    }


    @PostMapping("upload")
    @CheckPermission("add_file")
    public APIResponse<FileDto> upload(FileDto dto) throws ValidationException {
        //todo: implement upload logic
        return APIResponse.<FileDto>builder()
                .status(HttpStatus.OK)
                .data(null)
                .message("")
                .build();

    }

    @Override
    @CheckPermission("delete_file")
    public APIResponse<Boolean> delete(Long id) {
        return APIResponse.<Boolean>builder()
                .status(HttpStatus.OK)
                .data(service.delete(id))
                .message("")
                .build();
    }

    @Override
    @CheckPermission("list_file")
    public APIPanelResponse<List<FileDto>> getAll(Integer page, Integer size) {
        Page<FileDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<FileDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

}
