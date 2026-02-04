package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.command.CreateUserCommand;
import com.dardan.rent_tool.application.command.UpdateUserCommand;
import com.dardan.rent_tool.application.dto.DamageReportDTO;
import com.dardan.rent_tool.application.dto.IncomeMetricsDTO;
import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.dto.ToolIncomeDTO;
import com.dardan.rent_tool.application.dto.ToolRentCountDTO;
import com.dardan.rent_tool.application.dto.UserDTO;
import com.dardan.rent_tool.application.usecase.admin.IncomeMetricsUseCase;
import com.dardan.rent_tool.application.usecase.admin.ListDamageReportsUseCase;
import com.dardan.rent_tool.application.usecase.admin.ListPaymentsUseCase;
import com.dardan.rent_tool.application.usecase.admin.ListRentalsUseCase;
import com.dardan.rent_tool.application.usecase.admin.ProfitabilityMetricsUseCase;
import com.dardan.rent_tool.application.usecase.admin.ResolveDamageReportUseCase;
import com.dardan.rent_tool.application.usecase.admin.TopToolsMetricsUseCase;
import com.dardan.rent_tool.application.usecase.user.CreateUserUseCase;
import com.dardan.rent_tool.application.usecase.user.DeleteUserUseCase;
import com.dardan.rent_tool.application.usecase.user.GetUserUseCase;
import com.dardan.rent_tool.application.usecase.user.ListUsersUseCase;
import com.dardan.rent_tool.application.usecase.user.UpdateUserUseCase;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.CreateUserRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.UpdateUserRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.DamageReportResponse;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.IncomeMetricsResponse;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.PaymentResponse;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.RentalResponse;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.ToolIncomeResponse;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.ToolRentCountResponse;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListRentalsUseCase listRentalsUseCase;
    private final ListPaymentsUseCase listPaymentsUseCase;
    private final ListDamageReportsUseCase listDamageReportsUseCase;
    private final ResolveDamageReportUseCase resolveDamageReportUseCase;
    private final IncomeMetricsUseCase incomeMetricsUseCase;
    private final TopToolsMetricsUseCase topToolsMetricsUseCase;
    private final ProfitabilityMetricsUseCase profitabilityMetricsUseCase;

    public AdminController(CreateUserUseCase createUserUseCase,
                           ListUsersUseCase listUsersUseCase,
                           GetUserUseCase getUserUseCase,
                           UpdateUserUseCase updateUserUseCase,
                           DeleteUserUseCase deleteUserUseCase,
                           ListRentalsUseCase listRentalsUseCase,
                           ListPaymentsUseCase listPaymentsUseCase,
                           ListDamageReportsUseCase listDamageReportsUseCase,
                           ResolveDamageReportUseCase resolveDamageReportUseCase,
                           IncomeMetricsUseCase incomeMetricsUseCase,
                           TopToolsMetricsUseCase topToolsMetricsUseCase,
                           ProfitabilityMetricsUseCase profitabilityMetricsUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.getUserUseCase = getUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.listRentalsUseCase = listRentalsUseCase;
        this.listPaymentsUseCase = listPaymentsUseCase;
        this.listDamageReportsUseCase = listDamageReportsUseCase;
        this.resolveDamageReportUseCase = resolveDamageReportUseCase;
        this.incomeMetricsUseCase = incomeMetricsUseCase;
        this.topToolsMetricsUseCase = topToolsMetricsUseCase;
        this.profitabilityMetricsUseCase = profitabilityMetricsUseCase;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        UserDTO dto = createUserUseCase.execute(new CreateUserCommand(
            request.getFullName(),
            request.getEmail(),
            request.getPhone(),
            request.getRole()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(dto));
    }

    @GetMapping("/users")
    public List<UserResponse> listUsers() {
        return listUsersUseCase.execute().stream().map(this::toResponse).toList();
    }

    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable UUID id) {
        return toResponse(getUserUseCase.execute(id));
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest request) {
        UserDTO dto = updateUserUseCase.execute(new UpdateUserCommand(
            id,
            request.getFullName(),
            request.getPhone(),
            request.getRole()
        ));
        return toResponse(dto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rentals")
    public List<RentalResponse> listRentals() {
        return listRentalsUseCase.execute().stream().map(this::toResponse).toList();
    }

    @GetMapping("/payments")
    public List<PaymentResponse> listPayments() {
        return listPaymentsUseCase.execute().stream().map(this::toResponse).toList();
    }

    @GetMapping("/damage-reports")
    public List<DamageReportResponse> listDamageReports() {
        return listDamageReportsUseCase.execute().stream().map(this::toResponse).toList();
    }

    @PatchMapping("/damage-reports/{id}/resolve")
    public DamageReportResponse resolveDamageReport(@PathVariable UUID id) {
        return toResponse(resolveDamageReportUseCase.execute(id));
    }

    @GetMapping("/metrics/income")
    public IncomeMetricsResponse incomeMetrics() {
        IncomeMetricsDTO dto = incomeMetricsUseCase.execute();
        return new IncomeMetricsResponse(dto.getTotalIncome());
    }

    @GetMapping("/metrics/top-tools")
    public List<ToolRentCountResponse> topToolsMetrics() {
        return topToolsMetricsUseCase.execute().stream()
            .map(dto -> new ToolRentCountResponse(dto.getToolId(), dto.getTotal()))
            .toList();
    }

    @GetMapping("/metrics/profitability")
    public List<ToolIncomeResponse> profitabilityMetrics() {
        return profitabilityMetricsUseCase.execute().stream()
            .map(dto -> new ToolIncomeResponse(dto.getToolId(), dto.getTotalIncome()))
            .toList();
    }

    private UserResponse toResponse(UserDTO dto) {
        return new UserResponse(dto.getId(), dto.getFullName(), dto.getEmail(), dto.getPhone(), dto.getRole());
    }

    private RentalResponse toResponse(RentalDTO dto) {
        return new RentalResponse(
            dto.getId(),
            dto.getToolId(),
            dto.getCustomerId(),
            dto.getProviderId(),
            dto.getStartDate(),
            dto.getEndDate(),
            dto.getTotalAmount(),
            dto.getStatus(),
            dto.getCreatedAt()
        );
    }

    private PaymentResponse toResponse(PaymentDTO dto) {
        return new PaymentResponse(
            dto.getId(),
            dto.getRentalId(),
            dto.getAmount(),
            dto.getStatus(),
            dto.getMethod(),
            dto.getCreatedAt()
        );
    }

    private DamageReportResponse toResponse(DamageReportDTO dto) {
        return new DamageReportResponse(
            dto.getId(),
            dto.getRentalId(),
            dto.getToolId(),
            dto.getDescription(),
            dto.isResolved(),
            dto.getCreatedAt()
        );
    }
}
