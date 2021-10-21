section .data
codes:
    db      '0123456789ABCDEF'

section .text
global _start
_start:

    mov rdi, 0x1122334455667788;
    call print_hex;
    mov rdi, 0x0000000000000228;
    call print_hex;
    mov rdi, 0x0000000000001337;
    call print_hex;
    
    
    call exit;
    
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
