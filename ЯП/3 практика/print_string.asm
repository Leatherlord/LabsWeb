print_string:

    xor rax, rax

  .loop:
    lea rsi, [rdi+rax]
    cmp byte [rdi+rax], 0
    je .end
    inc rax
    push rax
    push rdi
    push rdx
    mov rax, 1
    mov rdi, 1
    mov rdx, 1
    push rcx
    syscall
    pop rcx
    pop rdx
    pop rdi
    pop rax
    jmp .loop
    
  .end:
    ret