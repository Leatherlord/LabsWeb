parse_uint:
    

    mov r9, 10
    xor rdx, rdx
    xor rax, rax
    jmp .A
    
  .A:
    cmp byte[rdi], 48
    jge .B
    ret
    
  .B:
    cmp byte[rdi], 57
    jle .C
    ret
    
  .C:
    push rdx
    mul r9
    pop rdx
    mov r10, [rdi]
    push 0
    mov [rsp], r10b
    pop r10
    add rax, r10
    sub rax, 48
    inc rdx
    inc rdi
    jmp .A
    
    
read_word:
    
    push rdi
    push rsi
    push rdi
    push rsi
    
  .A:
    call read_char
    cmp rax, 0x20
    je .A
    cmp rax, 0x9
    je .A
    cmp rax, 0xA
    je .A
    
    pop rsi
    pop rdi
    cmp rax, 0
    je .end
    
    dec rsi
    mov [rdi], rax
    inc rdi
    jmp .B
    
  .A1:
    cmp byte[rdi], 48
    jge .B
    ret
    
  .B1:
    cmp byte[rdi], 57
    jle .C
    ret
    
  .C:
    push rdx
    mul r9
    pop rdx
    mov r10, [rdi]
    push 0
    mov [rsp], r10b
    pop r10
    add rax, r10
    sub rax, 48
    inc rdx
    inc rdi
    jmp .A
    
  .B:
    push rdi
    push rsi
    call read_char
    pop rsi
    pop rdi
    
    
    cmp rax, 0x20
    je .end
    cmp rax, 0x9
    je .end
    cmp rax, 0xA
    je .end
    cmp rax, 0
    je .end
    
    
    dec rsi
    mov [rdi], rax
    inc rdi
    cmp rsi, 0
    jne .B
    pop rsi
    pop rdi
    mov rax, 0
    mov rdx, 0  
    ret
    
    
  .end:
    mov byte[rdi], 0
    pop rdx
    pop rax
    sub rdx, rsi
    ret
    
    
    
    
    
    
    
    
    
check:
   
    ;пусть в rdi передали адрес
    jmp .A
    

  .A: ;ищем начало чиселки
    inc rdi
    mov rax, rdi
    dec rax
    cmp [rax], 0x20
    je .A
    cmp [rax], 0x9
    je .A
    cmp [rax], 0xA
    je .A

    cmp [rax], 0
    je .end
    
    cmp [rax], 43
    jne .min ;не плюс - проверим минус
    jmp .cnt
    
    .min:
    cmp [rax], 45
    jne .B ;не он - идем дальше
    
    .cnt: ;если знак - передаем дальше следующий символ
    jmp .D
    
    .B:
    cmp byte[rax], 48
    jge .C
    jmp .error ;если нет - прыгаем к ошибке
    
    .C:
    cmp byte[rax], 57
    jle .D
    jmp .error ;если нет - прыгаем к ошибке
    
    
  .D: ;проверяем чиселки
    
    .E:
    cmp byte[rax], 48
    jge .E
    jmp .end ;если нет - проверим конец строки
    
    .F:
    cmp byte[rax], 57
    jle .G
    jmp .end ;если нет - проверим конец строки    
    
    .G: ;если чиселка, проверим следующий символ
    inc rdi
    jmp .D
    
  .end:
    
    mov rax, rdi
    cmp [rax], 0x20
    je .good ;проверка пройдена
    cmp [rax], 0x9
    je .good ;проверка пройдена
    cmp [rax], 0xA
    je .good ;проверка пройдена
    cmp [rax], 0
    je .good ;проверка пройдена
    jmp .error ;проверка не пройдена
    
  .error: ;как-то реализуем ошибку - не супер сложно
    