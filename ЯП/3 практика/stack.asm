section .data
codes:
    db      '0123456789ABCDEF'

section .text
global _start
_start:

    call stack
    
    
    call exit
    
  print_hex:
    mov rax, rdi;

    mov rdi, 1
    mov rdx, 1
    mov rcx, 64

  .loop:
    push rax
    sub rcx, 4

    sar rax, cl
    and rax, 0xf

    lea rsi, [codes + rax]
    mov rax, 1

    push rcx
    syscall
    pop rcx

    pop rax

    test rcx, rcx
    jnz .loop
    ret

  exit:
    mov     rax, 60            
    xor     rdi, rdi
    syscall
    
  stack:
    sub rsp, 48
    
    mov rax, rsp
    add rax, 16
    mov [rax], 'aa'
    
    mov rdi, rax
    push rax
    call print_hex
    pop rax
    
    add rax, 16
    mov [rax], 'bb'
    
    mov rdi, rax
    push rax
    call print_hex
    pop rax
    
    add rax, 16
    mov [rax], 'ff'
    
    mov rdi, rax
    push rax
    call print_hex
    pop rax
    
    ret
