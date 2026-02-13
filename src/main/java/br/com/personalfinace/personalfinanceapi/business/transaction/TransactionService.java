package br.com.personalfinace.personalfinanceapi.business.transaction;

import br.com.personalfinace.personalfinanceapi.business.account.AccountService;
import br.com.personalfinace.personalfinanceapi.business.tag.TagService;
import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.business.transaction.dto.TransactionRequest;
import br.com.personalfinace.personalfinanceapi.business.transaction.dto.TransactionResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final TagService tagService;

    private TransactionResponse toResponse(Transaction transaction) {
        Set<TagResponse> tags = transaction.getTags().stream()
                .map(tag -> new TagResponse(
                        tag.getId(),
                        tag.getName(),
                        tag.getParent() != null ? tag.getParent().getId() : null,
                        tag.getPrimaryHexColor(),
                        tag.getSecondaryHexColor(),
                        null
                ))
                .collect(Collectors.toSet());

        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getAccount().getId(),
                transaction.getAccount().getName(),
                transaction.getParentTransaction() != null ? transaction.getParentTransaction().getId() : null,
                transaction.getTransferGroup() != null ? transaction.getTransferGroup().getId() : null,
                tags
        );
    }

    private Transaction toEntity(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionRequest.id());
        transaction.setDescription(transactionRequest.description());
        transaction.setAmount(transactionRequest.amount());
        transaction.setType(transactionRequest.transactionType());
        transaction.setDate(transactionRequest.date());

        transaction.addTags(tagService.getAllByIds(transactionRequest.getTagIds()));

        transaction.setAccount(accountService.getAccount(transactionRequest.accountId()));

        if(Objects.nonNull(transactionRequest.parentTransactionId())){
            transaction.setParentTransaction(transactionRepository.findById(transactionRequest.parentTransactionId()).orElse(null));
        }
        return transaction;
    }

    public TransactionResponse save(TransactionRequest transactionRequest) {
        Transaction transaction = toEntity(transactionRequest);
        return toResponse(transactionRepository.save(transaction)) ;
    }

    public List<Transaction> findAllByAccountId(Long accountId) {
        return transactionRepository.findAllByTagIncludingSubtags(accountId);
    }

    public TransactionResponse findById(Long id) {
        return toResponse(Objects.requireNonNull(transactionRepository.findById(id).orElse(null))) ;
    }

    public List<TransactionResponse> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(this::toResponse).collect(Collectors.toList());
    }


    public Response delete(Long id) {
        transactionRepository.deleteById(id);
        return new Response("Deleted transaction with id " + id);
    }
}
