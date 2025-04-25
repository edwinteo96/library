-- Postgres
CREATE TABLE borrower (
    id bigint PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Add index for faster lookups by email
CREATE INDEX idx_borrower_email ON borrower(email);


CREATE TABLE book (
    id bigint PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_isbn_identity UNIQUE (isbn, title, author)
);

-- Add indexes for faster searches
CREATE INDEX idx_book_isbn ON book(isbn);
CREATE INDEX idx_book_title ON book(title);
CREATE INDEX idx_book_author ON book(author);

-- Book copy table
CREATE TABLE book_copy (
    id bigint PRIMARY KEY,
    book_id bigint NOT NULL REFERENCES book(id),
    copy_number INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE' CHECK (status IN ('AVAILABLE', 'BORROWED', 'LOST', 'MAINTENANCE')),
    CONSTRAINT unique_copy_per_book UNIQUE (book_id, copy_number)
);

CREATE INDEX idx_book_copy_status ON book_copy(status);

-- Loan table
CREATE TABLE loan (
    id bigint PRIMARY KEY,
    book_copy_id bigint NOT NULL REFERENCES book_copy(id),
    borrower_id bigint NOT NULL REFERENCES borrower(id),
    loan_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    due_date TIMESTAMP WITH TIME ZONE NOT NULL,
    return_date TIMESTAMP WITH TIME ZONE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'RETURNED', 'OVERDUE', 'LOST'))
);

CREATE UNIQUE INDEX active_loan_per_copy ON loan (book_copy_id) WHERE (status = 'ACTIVE');
CREATE INDEX idx_loan_borrower ON loan(borrower_id);
CREATE INDEX idx_loan_status ON loan(status);
CREATE INDEX idx_loan_due_date ON loan(due_date);