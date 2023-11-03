package com.example.building_company.controller;

import com.example.building_company.constants.HttpStatuses;
import com.example.building_company.dto.UserDto;
import com.example.building_company.mapping.UserDtoMapper;
import com.example.building_company.model.User;
import com.example.building_company.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    /**
     * The method which returns a list of {@link User}'s.
     *
     * @return list of {@link UserDto}
     * @author Nazar Klimovych
     */
    @ApiOperation(value = "Get list of all users")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    /**
     * Method to find {@link User} by id.
     *
     * @return {@link UserDto}.
     * @author Nazar Klimovych
     */
    @ApiOperation(value = "Find User by id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    @GetMapping("/findById")
    public ResponseEntity<UserDto> findById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    /**
     * Method to find {@link User} by email.
     *
     * @return {@link UserDto}.
     * @author Nazar Klimovych
     */
    @ApiOperation(value = "Find user by email")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND),
    })
    @GetMapping("/findByEmail")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }

    /**
     * Method that allow you to save new {@link User}.
     *
     * @param userDto to save the {@link User}
     * @author Nazar Klimovych
     */
    @ApiOperation(value = "Save User")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
    })
    @PostMapping()
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        User user = userDtoMapper.convertToEntity(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    /**
     * Method for deleting {@link User} by its id.
     *
     * @param id {@link Long} with needed {@link User} id.
     * @return id of deleted {@link User}.
     * @author Nazar Klimovych
     */
    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }
}
